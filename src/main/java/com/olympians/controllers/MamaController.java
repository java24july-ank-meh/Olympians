package com.olympians.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

@Controller
public class MamaController {
	
	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;
	
	public MamaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MamaController(PersonImpl loggedIn, DaoInterface dao) {
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

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToLogin() {
		return "/pages/index.html";
	} 
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public String goHome() {
		return "/pages/homepage.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String user, String pass, HttpServletRequest req) {
		boolean valid = false;
		try {valid = dao.Login(user, pass);}
		catch(Exception e) {e.printStackTrace();}
		
		if(valid) {
		
			loggedIn.setUsername(user);
			loggedIn.setPassword(pass);
		
			System.out.println(loggedIn);
			return "redirect:homepage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/checkuser", method=RequestMethod.GET)
	public ResponseEntity<String> checkUser(){
		return ResponseEntity.ok(loggedIn.getUsername());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(HttpServletRequest req) {
		/*
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String username = req.getParameter("user1");
		String email = req.getParameter("email");
		String password = req.getParameter("pass1");
		
		loggedIn = new Person(fname, lname, username, password, email);
		System.out.println("In register: " + username);
		return "redirect:homepage"; */
		
		return "forward:usercontroller/new";
	}

	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public String updateuser(HttpServletRequest req) {
		return "/pages/homepage.html";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest req) {
		System.out.println("reached logout");
		HttpSession session = req.getSession();
		session.invalidate();
		System.out.println("after session invalidate, username is: " + loggedIn.getUsername());
		return "redirect:/";
	}
	
	@RequestMapping(value="/bookmarks", method=RequestMethod.GET)
	public String allBookmarks(HttpServletRequest req) {
		return "redirect:bookmarkcontroller/all";
	}
	@RequestMapping(value="/addbookmarks", method=RequestMethod.POST)
	public String addBookmarks(HttpServletRequest req){
		return "forward:bookmarkcontroller/add";
	}
	@RequestMapping(value="/bookmarks", method=RequestMethod.POST)
	public String editBookmarks(HttpServletRequest req){
		return "redirect:bookmarkcontroller/edit";
	}
	
	@RequestMapping(value="/retrievebookmark", method=RequestMethod.GET)
	public String retrieveBookmark(HttpServletRequest req) {
		return "redirect:bookmarkcontroller/retrieve";
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String allUserFields(HttpServletRequest req){
		return "redirect:usercontroller/all";
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.POST)
	public String editUserFields(HttpServletRequest req){
		return "redirect:usercontroller/edit";
	}
}
