package com.olympians.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void CreateUser(String fname, String lname, String username, String password, String email)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void InsertPerson(Person person) throws Exception {
		Session session = sf.getCurrentSession();
		//Transaction tx = session.beginTransaction();

		session.save(person);
		session.flush();
		//tx.commit();

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

	@Transactional
	public boolean Login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	public void CreateBookmark(String name, String address, String description, int pid, int rating, String category,
			String image) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void DeleteBookmark(int pid, int bmid) throws Exception {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Bookmark> SortbyName(int pid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Bookmark> SortByDate(int pid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Bookmark> SortByRating(int pid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void DeletePerson(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void ExportSingleBookmark() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void ImportSingleBookmark() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
