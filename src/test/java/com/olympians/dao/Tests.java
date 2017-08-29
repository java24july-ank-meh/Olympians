package com.olympians.dao;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;


public class Tests {

	@Test
	public void addDeleteEditPersonTest(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		
		// Test Add Person
		Person person = new Person("testPerson[" + rand + "]", "lname[" + rand + "]", "username[" + rand + "]", "pword[" + rand + "]", "email[" + rand + "]");
		try {
			dao.InsertPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Person person1 = dao.getPersonInfo(person.getUsername(), person.getPassword());
		
		assertEquals(person.getUsername(), person1.getUsername());
		
		// Test Edit Person
		dao.EditAccount(person, person.getFname(), null, "newUsername[" + rand + "]", null, null);
		person1 = dao.getPersonInfo("newUsername[" + rand + "]", person.getPassword());
		
		assertEquals(person.getPid(), person1.getPid());
		
		// Test Delete Person
		try {
			 dao.DeletePerson(person.getUsername(), person.getPassword(), person.getPid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person nullPerson = dao.getPersonInfo(person.getUsername(), person.getPassword());
		
		assertEquals(null, nullPerson);
		
	}
	
	@Test
	public void addDeleteEditBookmarkTest(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		List<Bookmark> bList = null;
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		Person person = new Person("testPerson[" + rand + "]", "lname[" + rand + "]", "username[" + rand + "]", "pword[" + rand + "]", "email[" + rand + "]");
		try {
			dao.InsertPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Test Add Bookmark
		try {
			List<Category> cList = dao.AllCategories();
			int anyCid = cList.get(0).getCid();
			dao.CreateBookmark("address", "address", "description", person, 1, anyCid, "image@imageurl");
			bList = dao.SortbyName(person.getPid());
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, bList.size());
		
		// Test Edit Bookmark
		dao.EditBookmark(null, 5, null, null , null, null);
		try {
			bList = dao.SortbyName(person.getPid());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(1, bList.size());
		assertEquals(5, bList.get(0).getRating());
		
		// Test Delete Bookmark
		try {
			dao.DeleteBookmark(person.getPid(), bList.get(0).getBmid());
			bList = dao.SortbyName(person.getPid());
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(0, bList.size());
		
	}
	

	
	
	
		
	
}
