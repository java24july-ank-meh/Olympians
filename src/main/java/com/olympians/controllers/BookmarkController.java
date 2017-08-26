package com.olympians.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

@Controller
@RequestMapping("/bookmarkcontroller")
public class BookmarkController {

	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;
	
	@RequestMapping("/all")
	public ResponseEntity<Object> allBookmarks(HttpServletRequest req){
		return null;
	}
	
	@RequestMapping("/add")
	public ResponseEntity<Object> addBookmark(HttpServletRequest req){
		return null;
	}
	
	@RequestMapping("/edit")
	public ResponseEntity<Object> editBookmark(HttpServletRequest req){
		return null;
	}
	
}
