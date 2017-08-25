package com.olympians.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	public void CreateUser(String fname, String lname, String username, String pword, String email)
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
		
		

	}


	@Transactional
	public void UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String[] imgurData = ImgurContent.uploadByLink(url);
		session.persist(bookmark);
		bookmark.setImage(imgurData[0]);
		bookmark.setImageDeleteHash(imgurData[1]);

		session.saveOrUpdate(bookmark);
		session.flush();
		tx.commit();
	}

	@Transactional
	public void UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String[] imgurData = ImgurContent.uploadByFile(filePath);
		session.persist(bookmark);
		bookmark.setImage(imgurData[0]);
		bookmark.setImageDeleteHash(imgurData[1]);

		session.saveOrUpdate(bookmark);
		session.flush();
		tx.commit();
	}

	@Transactional
	public void EditAccount(Person person, String fname, String lname, String username, String password, String email) {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(person);
		
		if(fname != null) {person.setFname(fname);}
		if(lname != null) {person.setLname(lname);}
		if(username != null) {person.setUsername(username);}
		if(password != null) {person.setPassword(password);}
		if(email != null) {person.setEmail(email);}

		session.saveOrUpdate(person);
		session.flush();
		tx.commit();
		
	}

	@Transactional
	public void EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description) {
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
	public void CreateBookmark(String name, String address, String description, Person person, int rating, int category,
			String image) throws Exception {
		
		Session session = sf.getCurrentSession();
		Category temp = new Category();
		temp.setCid(category);
		Bookmark bookmark = new Bookmark( name, address, description, person, rating, temp, image);
		 
		//get category if exists otherwise create
		/*String hql = "FROM Category C WHERE C.cname = " + category;
		Query query = session.createQuery(hql);
		List<Category> results = query.list();
		
		if(results.isEmpty() == false) {
			
			bookmark = new Bookmark( name, address, description, person, rating, (Category)results.get(0), image);
		}
		else {
			Category c = new Category();
			c.setCname(name);
			sf.getCurrentSession();
			session.save(category);
			bookmark = new Bookmark( name, address, description, person, rating, c, image);
		}*/
		
		session.save(bookmark);
		session.flush();
	}

	@Transactional
	public void DeleteBookmark(int pid, int bmid) throws Exception {
		Session session = sf.getCurrentSession();
		
		Bookmark bookmark = session.get(Bookmark.class, bmid);
		//if(bookmark == null) {return false;}
		
		session.delete(bookmark);
		session.flush();
		
	}

	@Transactional
	public void ExportAllBookmarks() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void ImportAllBookmarks() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void AddCategory(String name) throws Exception {
		Category category = new Category();
		category.setCname(name);
		Session session = sf.getCurrentSession();
		session.save(category);
		session.flush();
		
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

	@Transactional
	public List<Bookmark> SortByRating(int pid) throws Exception {
		List<Bookmark> bookmarks;
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Bookmark bookmark;
		 
		String hql = "SELECT Category c FROM Category WHERE c.pid = " + pid; //does this work
		Query query = session.createQuery(hql);
		bookmarks = query.list();
		
		Collections.sort(bookmarks, new Bookmark.SortByRating());
		
		session.flush();
		tx.commit();
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
	public void ExportSingleBookmark() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void ImportSingleBookmark() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Category> AllCategories() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	// testing usage
	@Transactional
	public void InsertPerson(Person person) throws Exception {
		Session session = sf.getCurrentSession();
		//Transaction tx = session.beginTransaction();

		session.save(person);
		session.flush();
		//tx.commit();

	}

	@Override
	public Person getPersonInfo(String username, String pword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryInfo(String cname) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
