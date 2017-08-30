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
		/*
		PersonImpl person = new PersonImpl("chris", "palmour", "chp", "pass", "chp@gmal.com");
		Category category = new Category("social media");
		Bookmark bookmark1 = new Bookmark("Test Bookmark", "https://youtube.com", "this is a test",
				person, 4, category, "");
		Bookmark bookmark2 = new Bookmark("Test Bookmark 2", "https://twitter.com", "this is another test",
				person, 5, category, "");
		List<Bookmark> result = new ArrayList<>();
		result.add(bookmark1);
		result.add(bookmark2); */
		List<Bookmark> result = null;
		try{
			result = dao.GetListOfPBM(loggedIn);
			for(Bookmark b : result) {
				System.out.println(b);
			}
		}
		catch(Exception e) {e.printStackTrace();}
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping("/add")
	public String addBookmark(HttpServletRequest req){
		
		String title = req.getParameter("title");
		System.out.println(title);
		String url = req.getParameter("url");
		System.out.println(url);
		String categoryString = req.getParameter("category");
		/*
		System.out.println(categoryString);
		Category category = dao.getCategoryByName(categoryString);
		System.out.println(category); */
		String description = req.getParameter("desc");
		System.out.println(description);
		int rating = Integer.parseInt(req.getParameter("scoreSelect"));
		String image = req.getParameter("iurl");
		System.out.println(image);
		
		try{dao.CreateBookmark(title, url, description, loggedIn, rating, categoryString, image);}
		catch(Exception e) {e.printStackTrace();} 
		
		return "redirect:homepage";
	}
	
	@RequestMapping("/edit")
	public String editBookmark(HttpServletRequest req){
		
		int bmid = Integer.parseInt(req.getParameter("ebmid"));
		System.out.println("bmid: "+bmid);
		String name = req.getParameter("etitle");
		String address = req.getParameter("eurl");
		int rating = Integer.parseInt(req.getParameter("escoreSelect"));
		String category = req.getParameter("category");
		String description = req.getParameter("edesc");
		
		dao.EditBookmark(bmid, rating, category, name, address, description);
		return "redirect:homepage";
	}
	
	@RequestMapping("/retrieve")
	public ResponseEntity<Object> retrieveBookmark(HttpServletRequest req){
		
		String bmid = req.getParameter("bmid");
		System.out.println("received bmid: " + bmid);
		/*
		PersonImpl person = new PersonImpl("chris", "palmour", "chp", "pass", "chp@gmal.com");
		Category category = new Category("social media");
		Bookmark bookmark1 = new Bookmark("Test Bookmark", "https://youtube.com", "this is a test",
				person, 4, category, "");
		
		List<Bookmark> result = new ArrayList<>();
		result.add(bookmark1); */
		
		return null;
	}
	
	@RequestMapping("/sort")
	public ResponseEntity<Object> sortBookmarks(HttpServletRequest req){
		
		String criterion = req.getParameter("criterion");
		criterion = criterion.substring(1, criterion.length()-1);
		String direction = req.getParameter("direction");
		
		int pid = -1;
		try{pid = dao.GetPersonbyUserName(loggedIn.getUsername()).getPid();}
		catch(Exception e) {e.printStackTrace();}
		
		List<Bookmark> bookmarks = null;
		
		if(criterion.equalsIgnoreCase("Rating")) {
			
			try {bookmarks = dao.SortByRating(pid);}
			catch(Exception e) {e.printStackTrace();}
			
			return ResponseEntity.ok(bookmarks);
		}
		else if(criterion.equalsIgnoreCase("Date")) {
			try {bookmarks = dao.SortByDate(pid);}
			catch(Exception e) {e.printStackTrace();}
		}
		else if(criterion.equalsIgnoreCase("Category")) {
			try {bookmarks = dao.SortByCategory(pid);}
			catch(Exception e) {e.printStackTrace();}
		}
		else if(criterion.equalsIgnoreCase("Name")) {
			try {bookmarks = dao.SortbyName(pid);}
			catch(Exception e) {e.printStackTrace();}
		}
		
		return ResponseEntity.ok(bookmarks);
	}
	
}
