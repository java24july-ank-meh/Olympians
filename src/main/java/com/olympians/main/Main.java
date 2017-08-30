package com.olympians.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Bookmark;
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
		
		
		/*Person person2 = new Person();
		person2.setPid(22);
		List<Bookmark> blist= bmk.GetListOfPBM(person2);
		System.out.println(blist);
		System.out.println(blist.size());
		
		List<Bookmark> blist5 = bmk.SortbyName(3);
		System.out.println(blist5);
		System.out.println(blist5.size());
		System.out.println();
		System.out.println();*/
		
		
		/*Person p1 = bmk.getPersonInfo("ero","pass");
		List<Bookmark> blist= bmk.GetListOfPBM(person2);
		System.out.println(blist);*/
		
		/*Category cat;
		cat = bmk.getCategoryInfo(23);
		System.out.println(cat);*/
		
		//bmk.DeleteBookmark(3, 33);
		//bmk.AddCategory("Horror");
		/*bmk.AddCategory("Lifestyle");
		bmk.AddCategory("Beauty");
		bmk.EditBookmark(bookmark, rating, category, name, address, description)*/
		
		//System.out.println(person3);
		//bmk.EditAccount(person3, "eleazar", "rosales", "erosales", "pass", "erosales@gmail.com");
		//person3 = bmk.getPersonInfo("erosales", "pass");*/
		
		/*Bookmark bm = bmk.GetBookMarkInfo(3, 26);
		Category category;
		category = bmk.getCategoryInfo(2);
		System.out.println(category);
		System.out.println(bm);
		bmk.EditBookmark(bm, 4, category, "Tesla", "www.Testa.com", "This is tesla's main page");*/
		
		/*Person person1 = bmk.getPersonInfo("erosales", "pass");
		bmk.CreateBookmark("apple", "apple.com", "Best ever", person1, 3, 4, "somepic");*/
	
		/*System.out.println("new database info");
		
		bmk.DeletePerson("Ahern", "passw", 1);*/
		
		/*bmk.CreateUser("Alma", "Hernandez", "Ahern", "passw", "ahern@mail.com");
		bmk.AddCategory("Tech");
		bmk.AddCategory("LifeStyle");*/
		
		/*Person person = bmk.getPersonInfo("Ahern", "passw");
		Category category = bmk.getCategoryByName("LifeStyle");
		bmk.CreateBookmark("Tesla", "tesla.com", "best car ever", person, 5, 2, "somePic");*/
		
		bmk.DeletePerson("Ahern", "passw", 1);
		
		
	}

}
