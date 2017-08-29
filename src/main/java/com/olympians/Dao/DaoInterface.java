package com.olympians.Dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;
import com.olympians.beans.PersonImpl;

public interface DaoInterface {
	
	//works for now
	public boolean CreateUser(String fname, String lname, String username,
			 String pword, String email) throws Exception; // done
	
	public boolean UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception; //done
	
	public boolean UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception; //might be done
	//working
	public boolean EditAccount(Person person, String fname, String lname, String username, String password, String email); //done
	//working
	public boolean EditBookmark(int bmid, int rating, String categoryString, String name, String address, String description); //done
	// working 
	public boolean Login(String username, String pword) throws Exception;//done
	// working
	public boolean CreateBookmark(String name, String address, String description,
			Person person, int rating, String categoryString, String image) throws Exception; //done
	//working
	public boolean DeleteBookmark(int pid, int bmid) throws Exception; //done
	//working
	public boolean ExportAllBookmarks(String fileName, Person person) throws Exception; // done
	//working
	public boolean ImportAllBookmarks(String filePath, Person person) throws Exception; // done
	//working
	public boolean AddCategory(String name) throws Exception; //done
	//working
	public List<Bookmark> SortByCategory(int pid) throws Exception; // done
	//working
	public List<Bookmark> SortbyName(int pid) throws Exception; // done
	//working
	public List<Bookmark> SortByDate(int pid) throws Exception; // done
	//working
	public List<Bookmark> SortByRating(int pid) throws Exception; // done
	// working
	public boolean DeletePerson(String username, String password, int pid) throws Exception; //done
	// working
	public boolean ExportSingleBookmark(String fileName, Bookmark bookmark) throws Exception;  // done
	//working
	public boolean ImportSingleBookmark(String filePath, Person person) throws Exception; // done
	// working 
	public List<Category> AllCategories() throws Exception; //done
	// working
	public Person getPersonInfo(String username, String pword); // done
	// working
	public Category getCategoryInfo(int cid); // done
	// working
	public List<Bookmark> GetListOfPBM(Person person) throws Exception; // done
	//working
	public Bookmark GetBookMarkInfo(int pid, int bmid) throws Exception; //done
	
	public PersonImpl GetPersonbyUserName(String username) throws Exception;
	// testing function not really necessary
	public boolean InsertPerson(Person person) throws Exception; // done
	
	public Category getCategoryByName(String name);
}
