package com.olympians.controllers;

import java.util.ArrayList;
import java.util.List;

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
		List<Person> result = new ArrayList<>();
		Person toReturn = null;
		try{toReturn = dao.GetPersonbyUserName(loggedIn.getUsername());}
		catch(Exception e) {e.printStackTrace();}
		System.out.println("In allUserFields: " + toReturn);
		result.add(toReturn);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping("/edit")
	public String editUserFields(HttpServletRequest req){
		Person person = null;
		try{person = dao.GetPersonbyUserName(loggedIn.getUsername());}
		catch(Exception e ) {e.printStackTrace();}
		
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String username = req.getParameter("uname");
		String opword = req.getParameter("opword");
		String npword = req.getParameter("npword");
		String email = req.getParameter("email");
		
		if(!opword.equals(person.getPassword()) || !(opword.equals(npword))) {
		}
		else {
			dao.EditAccount(person, fname, lname, username, npword, email);
		}
		
		return "redirect:homepage";
		
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
