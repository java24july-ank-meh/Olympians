package com.olympians.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olympians.Dao.DaoInterface;
import com.olympians.beans.Person;

@Controller
@RequestMapping("usercontroller")
public class UserControler {

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
	
}
