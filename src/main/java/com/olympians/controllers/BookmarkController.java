package com.olympians.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

@Controller
@RequestMapping("/bookmarkcontroller")
public class BookmarkController {

	@Autowired
	Person loggedIn;
	
	@Autowired
	DaoInterface dao;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<Object> allBookmarks(HttpServletRequest req){
		/* 1. call dao's allBookmarks method (should return list of Bookmark objects)
		 * 2. return response entity of Bookmark Objects
		*/
		PersonImpl person = new PersonImpl("chris", "palmour", "chp", "pass", "chp@gmal.com");
		Category category = new Category("social media");
		Bookmark bookmark1 = new Bookmark("Test Bookmark", "https://youtube.com", "this is a test",
				person, 4, category, "");
		Bookmark bookmark2 = new Bookmark("Test Bookmark 2", "https://twitter.com", "this is another test",
				person, 5, category, "");
		List<Bookmark> result = new ArrayList<>();
		result.add(bookmark1);
		result.add(bookmark2);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping("/add")
	public ResponseEntity<Object> addBookmark(HttpServletRequest req){
		
		/*
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		int category = Integer.parseInt(req.getParameter("category"));
		String description = req.getParameter("desc");
		int rating = Integer.parseInt(req.getParameter("score"));
		String image = req.getParameter("iurl");
		
		try{dao.CreateBookmark(title, url, description, loggedIn, rating, category, image);}
		catch(Exception e) {e.printStackTrace();} */
		
		return null;
	}
	
	@RequestMapping("/edit")
	public ResponseEntity<Object> editBookmark(HttpServletRequest req){
		return null;
	}
	
	@RequestMapping("/retrieve")
	public ResponseEntity<Object> retrieveBookmark(HttpServletRequest req){
		PersonImpl person = new PersonImpl("chris", "palmour", "chp", "pass", "chp@gmal.com");
		Category category = new Category("social media");
		Bookmark bookmark1 = new Bookmark("Test Bookmark", "https://youtube.com", "this is a test",
				person, 4, category, "");
		
		List<Bookmark> result = new ArrayList<>();
		result.add(bookmark1);
		return ResponseEntity.ok(result);
	}
	
}
