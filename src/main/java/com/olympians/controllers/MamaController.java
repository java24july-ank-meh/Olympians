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

import com.olympians.beans.Person;

@Controller
public class MamaController {
	
	@Autowired
	Person loggedIn;

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
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(HttpServletRequest req) {
		return null;
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
	public ResponseEntity<Object> allBookmarks(HttpServletRequest req) {
		List<Object> result = new ArrayList<>();
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/addbookmarks", method=RequestMethod.POST)
	public ResponseEntity<Object> addBookmarks(HttpServletRequest req){
		List<Object> result = new ArrayList<>();
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/bookmarks", method=RequestMethod.POST)
	public ResponseEntity<Object> editBookmarks(HttpServletRequest req){
		List<Object> result = new ArrayList<>();
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public ResponseEntity<Object> allUserFields(HttpServletRequest req){
		List<Object> userFields = new ArrayList<>();
		return ResponseEntity.ok(userFields);
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.POST)
	public ResponseEntity<Object> editUserFields(HttpServletRequest req){
		List<Object> userFields = new ArrayList<>();
		System.out.println("Done");
		return ResponseEntity.ok(userFields);
	
	}

	
	
}
