package com.olympians.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		Person person = new Person();
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

	@Transactional
	public boolean EditAccount(Person old, String fname, String lname, String username, String password, String email) {
		Session session = sf.getCurrentSession();

		Person person = new Person(old);

		if (fname != null) {person.setFname(fname);}
		if (lname != null) {person.setLname(lname);}
		if (username != null) {person.setUsername(username);}
		if (password != null) {person.setPassword(password);}
		if (email != null) {person.setEmail(email);}

		session.update(person);
		session.flush();
		
		return true;
	}

	@Transactional
	public boolean EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description) {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(bookmark);
		
		if(rating != -1) {bookmark.setRating(rating);}  // -1 because int can't be null
		if(category != null) {bookmark.setCategory(category);}
		if(name != null) {bookmark.setName(name);}
		if(address != null) {bookmark.setAddress(address);}
		if(description != null) {bookmark.setDescription(description);}

		session.saveOrUpdate(bookmark);
		session.flush();
		tx.commit();
		return true;
	}
	// working
	@Transactional
	public boolean Login(String username, String pword) throws Exception {
		Session session = sf.getCurrentSession();
		Person person;
		String hql = "FROM Person P WHERE P.username = '"+username+"'"+
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

	@Transactional
	public boolean CreateBookmark(String name, String address, String description, Person person, int rating, int category,
			String image) throws Exception {
		
		Session session = sf.getCurrentSession();
		Category temp = new Category();
		temp.setCid(category);
		Bookmark bookmark = new Bookmark( name, address, description, person, rating, temp, image);
		
		session.save(bookmark);
		session.flush();
		
		return true;
	}

	@Transactional
	public boolean DeleteBookmark(int pid, int bmid) throws Exception {
		Session session = sf.getCurrentSession();
		
		Bookmark bookmark = session.get(Bookmark.class, bmid);
		//if(bookmark == null) {return false;}
		
		session.delete(bookmark);
		session.flush();
		
		return true;
	}

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
                
                 bookmark.setBmid( newBmid );
                 bookmark.setName(bookmarkArray[3]);
                 bookmark.setAddress(bookmarkArray[5]);
                 bookmark.setDescription(bookmarkArray[7]);
                 bookmark.setPerson(person);
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

	@Transactional
	public boolean AddCategory(String name) throws Exception {
		Category category = new Category();
		category.setCname(name);
		Session session = sf.getCurrentSession();
		session.save(category);
		session.flush();
		return true;
		
	}

	@Transactional
	public List<Bookmark> SortByCategory(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Bookmark bookmark;
		 
		String hql = "SELECT Category c FROM Category WHERE c.pid = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByCategory());
		
		session.flush();
		tx.commit();
		return bookmarks;
	}

	@Transactional
	public List<Bookmark> SortbyName(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Bookmark bookmark;
		 
		String hql = "SELECT Category c FROM Category WHERE c.pid = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByName());
		
		session.flush();
		tx.commit();
		return bookmarks;
	}

	@Transactional
	public List<Bookmark> SortByDate(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Bookmark bookmark;
		 
		String hql = "SELECT Category c FROM Category WHERE c.pid = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByDate());
		
		session.flush();
		tx.commit();
		return bookmarks;
	}
	//working
	@Transactional
	public List<Bookmark> SortByRating(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Bookmark bookmark;
		 Person person = new Person();
		 person.setPid(pid);
		String hql = "FROM Bookmark b WHERE b.person = "+pid+""; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByRating());
		
		session.flush();
		return bookmarks;
	}

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
        
         bookmark.setBmid( newBmid );
         bookmark.setName(bookmarkArray[3]);
         bookmark.setAddress(bookmarkArray[5]);
         bookmark.setDescription(bookmarkArray[7]);
         bookmark.setPerson(person);
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
		Person person = new Person();
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
	public Category getCategoryInfo(String cname) {
		Session session = sf.getCurrentSession();
		Category category = new Category();
		String hql = "FROM Category C WHERE C.cname = '"+cname+"'";
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
		String hql = "FROM Bookmark B Where B.person = "+person.getPid();
		Query query = session.createQuery(hql);
		clist = query.list();
		session.flush();
		return clist;
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

}
