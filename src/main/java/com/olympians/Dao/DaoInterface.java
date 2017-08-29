package com.olympians.Dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;

@SuppressWarnings("unused")
public interface DaoInterface {
	
	//works for now
	public boolean CreateUser(String fname, String lname, String username,
			 String pword, String email) throws Exception; 
	
	public boolean UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception; 
	
	public boolean UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception; 
	//working
	public boolean EditAccount(Person person, String fname, String lname, String username, String password, String email); 
	//working
	public boolean EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description); 
	// working 
	public boolean Login(String username, String pword) throws Exception;
	// working
	public boolean CreateBookmark(String name, String address, String description,
			Person person, int rating, int category, String image) throws Exception; 
	//working
	public boolean DeleteBookmark(int pid, int bmid) throws Exception; 
	//working
	public boolean ExportAllBookmarks(String fileName, Person person) throws Exception; 
	//working
	public boolean ImportAllBookmarks(String filePath, Person person) throws Exception; 
	//working
	public boolean AddCategory(String name) throws Exception; 
	//working
	public List<Bookmark> SortByCategory(int pid) throws Exception; 
	//working
	public List<Bookmark> SortbyName(int pid) throws Exception; 
	//working
	public List<Bookmark> SortByDate(int pid) throws Exception; 
	//working
	public List<Bookmark> SortByRating(int pid) throws Exception; 
	// working
	public boolean DeletePerson(String username, String password, int pid) throws Exception; 
	// working
	public boolean ExportSingleBookmark(String fileName, Bookmark bookmark) throws Exception;  
	//working
	public boolean ImportSingleBookmark(String filePath, Person person) throws Exception; 
	// working 
	public List<Category> AllCategories() throws Exception; 
	// working
	public Person getPersonInfo(String username, String pword); 
	// working
	public Category getCategoryInfo(int cid); 
	// working
	public List<Bookmark> GetListOfPBM(Person person) throws Exception; 
	//working
	public Bookmark GetBookMarkInfo(int pid, int bmid) throws Exception; 
	
	// testing function not really necessary
	public boolean InsertPerson(Person person) throws Exception; 
}
