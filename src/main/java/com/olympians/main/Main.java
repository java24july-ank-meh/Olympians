package com.olympians.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface bmk =
				(DaoInterface)ctx.getBean("bmrk");
		
		
		Person person = new Person("Eleazar", "Rosales", "erosales",
				 "pass", "e@mail.com");
		
		//bmk.InsertPerson(person);
		System.out.println(person);
		System.out.println("finished");
		
		//bmk.AddCategory("News");
		System.out.println("check the category table");
		
		//bmk.CreateUser("Diplo", "Diplo", "dip", "pwd", "dip@mail.com");
		
		boolean result;
		result = bmk.Login("erosales", "pass");
		System.out.println(result);
		
		
		

	}

}
