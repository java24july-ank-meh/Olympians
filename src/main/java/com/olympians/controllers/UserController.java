package com.olympians.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;

@Controller
@RequestMapping("/usercontroller")
public class UserController {

	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;
	
	@RequestMapping("/all")
	public ResponseEntity<Object> allUserFields(HttpServletRequest req){
		return null;
	}
	
	@RequestMapping("/edit")
	public ResponseEntity<Object> editUserFields(HttpServletRequest req){
		return null;
	}
	
	@RequestMapping("/new")
	public String newUser(HttpServletRequest req) {
		//forward:url lets you forward request from one controller to another
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String username = req.getParameter("user1");
		String email = req.getParameter("email");
		String password = req.getParameter("pass1");
		
		loggedIn = new Person(fname, lname, username, password, email);
		System.out.println("In register: " + loggedIn.getUsername());
		return "redirect:/homepage";
	}
	
}
