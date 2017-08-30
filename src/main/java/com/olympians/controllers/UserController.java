package com.olympians.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

@Controller
@RequestMapping("/usercontroller")
public class UserController {

	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;
	
	
	
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserController(Person loggedIn, DaoInterface dao) {
		super();
		this.loggedIn = loggedIn;
		this.dao = dao;
	}

	public Person getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Person loggedIn) {
		this.loggedIn = loggedIn;
	}

	public DaoInterface getDao() {
		return dao;
	}

	public void setDao(DaoInterface dao) {
		this.dao = dao;
	}

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
		
		loggedIn.setFname(fname);
		loggedIn.setLname(lname);
		loggedIn.setUsername(username);
		loggedIn.setEmail(email);
		loggedIn.setPassword(password);
		
		try {dao.CreateUser(fname, lname, username, password, email);}
		catch(Exception e) {return "redirect:/";}
		
		return "redirect:/homepage";
	}
	
}
