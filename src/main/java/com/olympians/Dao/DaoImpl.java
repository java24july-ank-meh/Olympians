package com.olympians.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaQuery;

import com.olympians.Imgur.ImgurContent;
import com.olympians.beans.Bookmark;
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
		Transaction tx = session.beginTransaction();

		session.save(person);

		tx.commit();

	}

	@Override
	public void UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception {
		Session session = sf.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		/*String[] imgurData = ImgurContent.uploadByLink(url);
		String hql = "UPDATE BOOKMARK set salary = :salary "  + 
	             "WHERE id = :employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("salary", 1000);
		query.setParameter("employee_id", 10);
		int result = query.executeUpdate();*/
		
		tx.commit();
	}

	@Override
	public void UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception {
		// TODO Auto-generated method stub

	}

}
