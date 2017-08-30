package com.olympians.dao;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
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
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		
		// Test Add Person
		System.out.println("## Testing Add Person ##");
		
		Person person = new Person("testPerson[" + rand + "]", "lname[" + rand + "]", "username[" + rand + "]", "pword[" + rand + "]", "email[" + rand + "]");
		try {
			dao.InsertPerson(person); 	// we won't actually use this method when making a person
		} catch (Exception e) {			// we will use the CreateUser() method
			e.printStackTrace();
		}
		Person person1 = dao.getPersonInfo(person.getUsername(), person.getPassword());
		System.out.println(person1);
		assertEquals(person.getUsername(), person1.getUsername());
		
		// Test Edit Person
		System.out.println("###Testing edit Person###");
		try {
			dao.EditAccount(person1, "Testuser", null, "newUsername[" + rand + "]", null, "testedit@mail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		person1 = dao.getPersonInfo("newUsername[" + rand + "]", person.getPassword());
		assertEquals(person1.getUsername(), "newUsername[" + rand + "]");
		System.out.println(person1);
		// Test delete person
		System.out.println("###Testing Delete Person###");
		try {
			dao.DeletePerson(person1.getUsername(), person1.getPassword(), person1.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Person notAPerson = dao.getPersonInfo(person1.getUsername(), person1.getPassword());
		assertEquals(null, notAPerson);
		System.out.println(notAPerson);
	}
	
	@Test
	public void addDeleteEditBookmarkTest(){
		@SuppressWarnings("resource")
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
		person = dao.getPersonInfo(person.getUsername(), person.getPassword());
		// Test Add Bookmark
		System.out.println("###Testing Add Bookmark###");
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
		System.out.println("###Testing Edit Bookmark###");
		
		dao.EditBookmark(bList.get(0), 5, null, null , null, null);
		try {
			bList = dao.SortbyName(person.getPid());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		assertEquals(1, bList.size());
		assertEquals(5, bList.get(0).getRating());
		
		// Test Delete Bookmark
		System.out.println("###Testing Delete Bookmark###");
		
		try {
			dao.DeleteBookmark(person.getPid(), bList.get(0).getBmid());
			bList = dao.SortbyName(person.getPid());
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			dao.DeletePerson(person.getUsername(), person.getPassword(), person.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(0, bList.size());
		
	}
	
	@Test
	public void addCategory(){
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		
		// Test Add Category
		System.out.println("## Testing Add Category ##");
		
		List<Category> catList =  null;
		boolean cExists = false;
		String name = "testCat["+rand+"]";
		
		try {
			dao.AddCategory(name);
			catList = dao.AllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Category temp = new Category();
		for(Category c : catList) {
			if(c.getCname().equals(name)) {
				temp.setCid(c.getCid());
				temp.setCname(c.getCname());
				cExists = true;
				break;
			}
		}
		
		assertEquals(true,cExists);
		
	}
	
	@Test
	public void uploadImagebyURL(){
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		Bookmark bookmark = null;
		List<Bookmark> bList = null;
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		
		//Create Person
		Person person = new Person("testPerson[" + rand + "]", "lname[" + rand + "]", "username[" + rand + "]", "pword[" + rand + "]", "email[" + rand + "]");
		try {
			dao.InsertPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create Bookmark
		System.out.println("## Testing Add Bookmark ##");
		
		try {
			List<Category> cList = dao.AllCategories();
			int anyCid = cList.get(0).getCid();
			dao.CreateBookmark("address", "address", "description", person, 1, anyCid, "image@imageurl");
			bList = dao.SortbyName(person.getPid());
			bookmark = bList.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String link = "http://www.ikea.com/gb/en/images/products/ingolf-chair-brown-black__0355482_pe547815_s5.jpg";
		try {
			dao.UploadImageByLink(bookmark, link);
			bList = dao.SortbyName(person.getPid());
			bookmark = bList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Check that: " 
				+ "\nURL 1: " + link  
				+ "\nURL 2: " + bookmark.getImage() 
				+ "\nare both the same picture of a chair");
	}
	
	@Test
	public void testGettingPersonInfo() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r);
		System.out.println("###TestingGetPersonInfo###");
		try {
			dao.CreateUser("testPerson[" + rand + "]", "lname[" + rand + "]", "username[" + rand + "]",
					"pword[" + rand + "]", "email[" + rand + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Person person = dao.getPersonInfo("username[" + rand + "]", "pword[" + rand + "]");
		System.out.println(person);
		assertNotNull(person);
		System.out.println(person);
		try {
			dao.DeletePerson(person.getUsername(), person.getPassword(), person.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetCategoryInfo() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		Random random = new Random();
		int r = random.nextInt(9999);
		String rand = Integer.toString(r); 
		System.out.println("###TestGettingCategoryInfo###");
		
		try {
			dao.AddCategory("cat"+rand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Category temp = dao.getCategoryByName("cat"+rand);
		assertNotNull(temp);
	}
	
	@Test
	public void testGettingAllCategories() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		System.out.println("###TestingGettingAllCategories###");
		List<Category> clist = new ArrayList();
		try {
			clist = dao.AllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(clist);
		
		assertNotNull(clist);	
		
	}

}
