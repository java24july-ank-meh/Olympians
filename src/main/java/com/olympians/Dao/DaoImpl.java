package com.olympians.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import com.olympians.Imgur.ImgurContent;
import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

@Component("bmrk")
public class DaoImpl implements DaoInterface {

	@Autowired
	SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	//working
	@Transactional
	public boolean CreateUser(String fname, String lname, String username, String pword, String email)
			throws Exception {
		Session session = sf.getCurrentSession();
		Person person = new PersonImpl();
		person.setFname(fname);
		person.setLname(lname);
		person.setUsername(username);
		person.setPassword(pword);
		person.setEmail(email);
		session.save(person);
		session.flush();
		
		// need to deal with uniqueness of a username
		
		return true;

	}

	@Transactional
	public boolean UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String[] imgurData = ImgurContent.uploadByLink(url);
		session.persist(bookmark);
		bookmark.setImage(imgurData[0]);
		bookmark.setImageDeleteHash(imgurData[1]);

		session.saveOrUpdate(bookmark);
		session.flush();
		tx.commit();
		
		return true;
	}

	@Transactional
	public boolean UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String[] imgurData = ImgurContent.uploadByFile(filePath);
		session.persist(bookmark);
		bookmark.setImage(imgurData[0]);
		bookmark.setImageDeleteHash(imgurData[1]);

		session.saveOrUpdate(bookmark);
		session.flush();
		tx.commit();
		
		return true;
	}
	//working
	@Transactional
	public boolean EditAccount(Person old, String fname, String lname, String username, String password, String email) {
		Session session = sf.getCurrentSession();

		Person person = new PersonImpl(old);

		if (fname != null) {person.setFname(fname);}
		if (lname != null) {person.setLname(lname);}
		if (username != null) {person.setUsername(username);}
		if (password != null) {person.setPassword(password);}
		if (email != null) {person.setEmail(email);}

		session.update(person);
		session.flush();
		
		return true;
	}
	//working
	@Transactional
	public boolean EditBookmark(int bmid, int rating, String categoryString, String name, String address, String description, String image) {
		Session session = sf.getCurrentSession();
		
		Bookmark bookmark = session.get(Bookmark.class, bmid);
		Category category = this.getCategoryByName(categoryString);
		System.out.println("bmid: " + bmid);
		if(rating != -1) {bookmark.setRating(rating);}  // -1 because int can't be null
		if(category != null) {bookmark.setCategory(category);}
		if(name != null) {bookmark.setName(name);}
		if(address != null) {bookmark.setAddress(address);}
		if(description != null) {bookmark.setDescription(description);}
		if(image != null) {bookmark.setImage(image);}

		session.update(bookmark);
		session.flush();
		return true;
	}
	// working
	@Transactional
	public boolean Login(String username, String pword) throws Exception {
		Session session = sf.getCurrentSession();
		Person person;
		String hql = "FROM PersonImpl P WHERE P.username = '"+username+"'"+
		" AND P.pword = '"+pword+"'";
		Query query = session.createQuery(hql);
		List<Person> results = query.list();
		
		if(results.isEmpty() == false) {
			System.out.println("Login was successful");
			return true;
		}
		else {
			System.out.println("Either Username or Password doesn't match");
			return false;
		}
	}
	//working
	@Transactional
	public boolean CreateBookmark(String name, String address, String description, Person person, int rating, String categoryString,
			String image) throws Exception {
		
		Session session = sf.getCurrentSession();
		PersonImpl personimpl = this.GetPersonbyUserName(person.getUsername());
		
		session.persist(personimpl);
		session.flush();
		
		Category category = this.getCategoryByName(categoryString);
		session.persist(category);
		session.flush();
		
		Bookmark bookmark = new Bookmark(StringUtils.capitalize(name), address, description, personimpl, rating, category, image);
		
		session.persist(bookmark);
		session.flush();
		
		return true;
	}
	// working
	@Transactional
	public boolean DeleteBookmark(int pid, int bmid) throws Exception {
		Session session = sf.getCurrentSession();
		
		Bookmark bookmark = session.get(Bookmark.class, bmid);
		//if(bookmark == null) {return false;}
		
		session.delete(bookmark);
		session.flush();
		
		return true;
	}
	//working
	@Transactional
	public boolean ExportAllBookmarks(String fileName, Person person) throws Exception {
		List<Bookmark> bList = SortbyName(person.getPid());
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (Bookmark bookmark : bList) {
				writer.println(bookmark.toString());
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
		
	}
	//working
	@Transactional
	public boolean ImportAllBookmarks(String filePath, Person person) throws Exception {
		Session session = sf.getCurrentSession();
        
        String line;
        String[] bookmarkArray = new String[16];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                 bookmarkArray = line.split("||\\=");
                
                 Bookmark bookmark = new Bookmark();
                 Category category = null;
                 int newBmid = Integer.parseInt(bookmarkArray[1]);
                 int newRating = Integer.parseInt(bookmarkArray[11]);
                
                 List<Category> cList = AllCategories();
                 boolean found = false;
                 for(Category c : cList) {
                     if(c.getCname() == bookmarkArray[13]) {
                         category = c;
                         found = true;
                     }
                 }
                 if(found == false) {
                     category = new Category(bookmarkArray[13]);
                 }
                 
                 PersonImpl personimpl = (PersonImpl) person;
                
                 bookmark.setBmid( newBmid );
                 bookmark.setName(bookmarkArray[3]);
                 bookmark.setAddress(bookmarkArray[5]);
                 bookmark.setDescription(bookmarkArray[7]);
                 bookmark.setPerson(personimpl);
                 bookmark.setRating(newRating);
                 bookmark.setCategory(category);
                 bookmark.setCategory(category);
                 bookmark.setImage(bookmarkArray[15]);
                
                    session.save(bookmark);
                    session.flush();                    
             }
        }
        catch(Exception e) {
            return false;
        }

        return true;
		
	}
	//working
	@Transactional
	public boolean AddCategory(String name) throws Exception {
		Category category = new Category();
		category.setCname(name);
		Session session = sf.getCurrentSession();
		session.save(category);
		session.flush();
		return true;
		
	}
	//working
	@Transactional
	public List<Bookmark> SortByCategory(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Bookmark bookmark;
		 
		String hql = "FROM Bookmark b WHERE b.person = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByCategory());
		
		session.flush();
		return bookmarks;
	}
	//working
	@Transactional
	public List<Bookmark> SortbyName(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Bookmark bookmark;
		 
		String hql = "FROM Bookmark b WHERE b.person = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByName());
		
		session.flush();
		return bookmarks;
	}
	//working
	@Transactional
	public List<Bookmark> SortByDate(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Bookmark bookmark;
		 
		String hql = "FROM Bookmark b WHERE b.person = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByDate());
		
		session.flush();
		return bookmarks;
	}
	//working
	@Transactional
	public List<Bookmark> SortByRating(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Bookmark bookmark;
		 Person person = new PersonImpl();
		 person.setPid(pid);
		String hql = "FROM Bookmark b WHERE b.person = "+pid+""; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByRating());
		
		session.flush();
		return bookmarks;
	}
	//working
	@Transactional
	public boolean DeletePerson(String username, String password, int pid) throws Exception {
		if(Login(username, password) == false ) {
			return false;
		}
		Session session = sf.getCurrentSession();
		Person person = session.get(Person.class, pid);
		session.delete(person);
		session.flush();
		return true;
	}
	//working
	@Transactional
	public boolean ExportSingleBookmark(String fileName, Bookmark bookmark) throws Exception {
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(bookmark.toString());
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
		
	}
	//working
	@Transactional
	public boolean ImportSingleBookmark(String filePath, Person person) throws Exception {
		Session session = sf.getCurrentSession();
        String line;
        String[] bookmarkArray = new String[16];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
            if(line == null) {return false;}
        }
        catch(Exception e) {
            return false;
        }
        
         bookmarkArray = line.split("||\\=");
        
         Bookmark bookmark = new Bookmark();
         Category category = null;
         int newBmid = Integer.parseInt(bookmarkArray[1]);
         int newRating = Integer.parseInt(bookmarkArray[11]);
        
         List<Category> cList = AllCategories();
         boolean found = false;
         for(Category c : cList) {
             if(c.getCname() == bookmarkArray[13]) {
                 category = c;
                 found = true;
             }
         }
         if(found == false) {
             category = new Category(bookmarkArray[13]);
         }
        
         PersonImpl personimpl = (PersonImpl) person;
         
         bookmark.setBmid( newBmid );
         bookmark.setName(bookmarkArray[3]);
         bookmark.setAddress(bookmarkArray[5]);
         bookmark.setDescription(bookmarkArray[7]);
         bookmark.setPerson(personimpl);
         bookmark.setRating(newRating);
         bookmark.setCategory(category);
         bookmark.setCategory(category);
         bookmark.setImage(bookmarkArray[15]);
        
        session.save(bookmark);
        session.flush();
        
        return true;
		
	}
	//working
	@Transactional
	public List<Category> AllCategories() throws Exception {
		List<Category> clist;
		Session session = sf.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		clist = query.list();
		session.flush();
		return clist;
	}
	//Working
	@Transactional
	public Person getPersonInfo(String username, String pword) {
		Session session = sf.getCurrentSession();
		Person person = new PersonImpl();
		String hql = "FROM Person P WHERE P.username = '"+username+"'"+
		" AND P.pword = '"+pword+"'";
		Query query = session.createQuery(hql);
		List<Person> results = query.list();
		
		if(results.isEmpty() == false) {
			person = results.get(0);
			return person;
		}
		else {
			return null;
		}
		
		
	}
	//working
	@Transactional
	public Category getCategoryInfo(int cid) {
		Session session = sf.getCurrentSession();
		Category category = new Category();
		String hql = "FROM Category C WHERE C.cid = "+cid;
		Query query = session.createQuery(hql);
		List<Category> results = query.list();
		
		if(results.isEmpty() == false) {
			category = results.get(0);
			return category;
		}
		else {
			return null;
		}
	}
	//working
		@Transactional
		public Category getCategoryByName(String name) {
			Session session = sf.getCurrentSession();
			Category category = new Category();
			String hql = "FROM Category C WHERE C.cname = '"+ name + "'";
			Query query = session.createQuery(hql);
			List<Category> results = query.list();
			
			if(results.isEmpty() == false) {
				category = results.get(0);
				return category;
			}
			else {
				return null;
			}
		}
	//working
	@Transactional
	public List<Bookmark> GetListOfPBM(Person person) throws Exception {
		List<Bookmark> clist;
		Session session = sf.getCurrentSession();
		PersonImpl personimpl = this.GetPersonbyUserName(person.getUsername());
		String hql = "FROM Bookmark B Where B.person = "+personimpl.getPid();
		Query query = session.createQuery(hql);
		clist = query.list();
		session.flush();
		return clist;
	}
	//working

	@Transactional
	public Bookmark GetBookMarkInfo(int pid, int bmid) throws Exception {
		Session session = sf.getCurrentSession();
		Bookmark bm;
		String hql = "FROM Bookmark b WHERE b.person = "+pid+
				" AND b.bmid = "+bmid;
		Query query = session.createQuery(hql);
		List<Bookmark> results = query.list();
		
		if(results.isEmpty() == false) {
			bm = results.get(0);
			return bm;
		}
		else {
			return null;
		}
	}
	// testing usage
	@Transactional
	public boolean InsertPerson(Person person) throws Exception {
		Session session = sf.getCurrentSession();
		//Transaction tx = session.beginTransaction();

		session.save(person);
		session.flush();
		//tx.commit();
		return true;
	}

	@Transactional
	public PersonImpl GetPersonbyUserName(String username) throws Exception {
		Session session = sf.getCurrentSession();
		PersonImpl person = new PersonImpl();
		String hql = "From PersonImpl P WHERE P.username = '"+username + "'";
		Query query = session.createQuery(hql);
		List<PersonImpl> results = query.list();
		
		if(results.isEmpty() == false) {
			person = results.get(0);
		}
		return person;
	}
	
	@Transactional
    public int  getBookmarkInfo(String address, int pid) {
        Session session = sf.getCurrentSession();
        int bmid = 0;
        Bookmark bm = new Bookmark();
        String hql = "From Bookmark b WHERE b.address = '"+address+"' AND b.person = "+pid;
        Query query = session.createQuery(hql);
        List<Bookmark> results = query.list();
        
        if(results.isEmpty() == false) {
            bmid = results.get(0).getBmid();
        }
        return bmid;
	}

}
