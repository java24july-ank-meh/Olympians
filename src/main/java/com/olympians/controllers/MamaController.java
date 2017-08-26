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

@Controller
public class MamaController {
	
	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToLogin() {
		return "/pages/index.html";
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public String goHome() {
		System.out.println("In goHome(): " + loggedIn.getUsername());
		return "/pages/homepage.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String user, String pass, HttpServletRequest req) {
		
		loggedIn.setUsername(user);
		loggedIn.setPassword(pass);
		
		System.out.println(loggedIn);
		return "redirect:homepage";
	}
	
	@RequestMapping(value="/checkuser", method=RequestMethod.GET)
	public ResponseEntity<String> checkUser(){
		System.out.println("reached checkuser");
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
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "/pages/index.html";
	}
	
	@RequestMapping(value="/bookmarks", method=RequestMethod.GET)
	public String allBookmarks(HttpServletRequest req) {
		return "redirect:bookmarkcontroller/all";
	}
	@RequestMapping(value="/addbookmarks", method=RequestMethod.POST)
	public String addBookmarks(HttpServletRequest req){
		return "redirect:bookmarkcontroller/add";
	}
	@RequestMapping(value="/bookmarks", method=RequestMethod.POST)
	public String editBookmarks(HttpServletRequest req){
		return "redirect:bookmarkcontroller/edit";
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
