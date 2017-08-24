package com.olympians.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.olympians.beans.Person;
//import com.olympians.Dao.DaoInterface;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("beans.xml");
		//DaoInterface bmk =
		//		(DaoInterface)ctx.getBean("bmrk");
		
		
		Person person = new Person("Dillon", "Francis", "dfran",
				 "pwd", "dfran@mail.com");
		
		//bmk.InsertPerson(person);
		System.out.println(person);
		System.out.println("finished");
		

	}

}
