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
	
	/*	------------------ Person/User Methods	------------------  */
	public boolean CreateUser(String fname, String lname, String username,
			 String pword, String email) throws Exception; 

	public boolean Login(String username, String pword) throws Exception;
	public boolean EditAccount(Person person, String fname, String lname, String username, String password, String email); 
	public boolean DeletePerson(String username, String password, int pid) throws Exception; 

	
	/*	------------------ Bookmark Methods	------------------  */
	public boolean CreateBookmark(String name, String address, String description,
			Person person, int rating, int category, String image) throws Exception; 
	
	public boolean EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description); 
	public Bookmark GetBookMarkInfo(int pid, int bmid) throws Exception; 
	public boolean DeleteBookmark(int pid, int bmid) throws Exception; 
	
	public boolean ExportAllBookmarks(String fileName, Person person) throws Exception; 
	public boolean ImportAllBookmarks(String filePath, Person person) throws Exception; 
	
	public List<Bookmark> SortByCategory(int pid) throws Exception; 
	public List<Bookmark> SortbyName(int pid) throws Exception; 
	public List<Bookmark> SortByDate(int pid) throws Exception; 
	public List<Bookmark> SortByRating(int pid) throws Exception; 
	public List<Bookmark> GetListOfPBM(Person person) throws Exception; 
	
	public boolean ExportSingleBookmark(String fileName, Bookmark bookmark) throws Exception;  
	public boolean ImportSingleBookmark(String filePath, Person person) throws Exception; 
	
	
	/*	------------------ Category Methods	------------------  */
	public boolean AddCategory(String name) throws Exception; 
	public List<Category> AllCategories() throws Exception; 
	public Person getPersonInfo(String username, String pword); 
	public Category getCategoryInfo(int cid); 
	
	
	/*	------------------ Imgur Methods	------------------  */
	public boolean UploadImageByLink(Bookmark old, String url) throws Exception; 
	public boolean UploadImageByFile(Bookmark old, String filePath) throws Exception; 
	
	
	// testing function not really necessary
	public boolean InsertPerson(Person person) throws Exception; 
}
