package com.olympians.dao;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;


public class Tests {

	@Test
	public void addDeleteEditPersonTest(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface dao =
				(DaoInterface)ctx.getBean("bmrk");
		
		
		// Test Add Person
		Person person = new Person();
		person.setFname("testPerson");
		try {
			dao.InsertPerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Person person1 = dao.getPersonInfo(person.getUsername(), person.getPassword());
		
		assertEquals(person.getUsername(), person1.getUsername());
		
		// Test Edit Person
		dao.EditAccount(person, person.getFname(), null, "newUsername", null, null);
		person1 = dao.getPersonInfo("newUsername", person.getPassword());
		
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
	
	
	
	
	
		
	
}
