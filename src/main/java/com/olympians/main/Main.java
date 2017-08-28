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
		
		String test = "hello my name is eleazar Rosales";
		StringUtils.capitalize(test);
		System.out.println(StringUtils.capitalize(test));
		System.out.println(test);
		test = StringUtils.capitalize(test);
		System.out.println(test);
	}

}
