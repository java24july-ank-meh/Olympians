package com.olympians.Dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;

public interface DaoInterface {
	
	public void CreateUser(String fname, String lname, String username,
			 String password, String email) throws Exception; // not done
	
	public void InsertPerson(Person person) throws Exception; // done
	
	public void UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception; //done
	
	public void UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception; //might be done
	
	public void EditAccount(Person person, String fname, String lname, String username, String password, String email); //done
	
	public void EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description); //done
	
	public boolean Login(String username, String password) throws Exception;// not done
	
	public void CreateBookmark(String name, String address, String description,
			int pid, int rating, String category, String image) throws Exception; // not done
	
	public void DeleteBookmark(int pid, int bmid) throws Exception; // not done
	
	public void ExportAllBookmarks() throws Exception; // not done
	
	public void ImportAllBookmarks() throws Exception; // not done
	
	public void AddCategory(String name) throws Exception; //done
	
	public List<Bookmark> SortByCategory(int pid) throws Exception; // not done
	
	public List<Bookmark> SortbyName(int pid) throws Exception; // not done
	
	public List<Bookmark> SortByDate(int pid) throws Exception; // not done
	
	public List<Bookmark> SortByRating(int pid) throws Exception; // not done
	
	public void DeletePerson(String username, String password) throws Exception; // not done
	
	public void ExportSingleBookmark() throws Exception; // not done
	
	public void ImportSingleBookmark() throws Exception; // not done
	
}
