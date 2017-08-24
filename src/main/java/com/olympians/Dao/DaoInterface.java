package com.olympians.Dao;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.beans.Person;

public interface DaoInterface {
	
	public void CreateUser(String fname, String lname, String username,
			 String password, String email) throws Exception;
	public void InsertPerson(Person person) throws Exception;
	
	public void UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception; //done
	
	public void UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception; //might be done
	
	public void EditAccount(Person person, String fname, String lname, String username, String password, String email);
	
	public void EditBookmark(Bookmark bookmark, int rating, Category category, String name, String address, String description);

}
