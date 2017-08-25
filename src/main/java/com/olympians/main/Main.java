package com.olympians.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Category;
import com.olympians.beans.Person;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		DaoInterface bmk =
				(DaoInterface)ctx.getBean("bmrk");
		
		
		//Person person = new Person("E", "Ro", "ero",
		//		 "pass", "e@mail.com");
		
		//bmk.InsertPerson(person);
		//System.out.println(person);
		System.out.println("finished");
		
		//bmk.AddCategory("Funny");
		System.out.println("check the category table");
		
		
		Person person2 = new Person();
		person2.setPid(3);
		
		List<Category> clist = bmk.AllCategories();
		System.out.println(clist);
	}

}
