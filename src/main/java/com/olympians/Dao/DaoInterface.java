package com.olympians.Dao;

import com.olympians.beans.Bookmark;
import com.olympians.beans.Person;

public interface DaoInterface {
	
	public void CreateUser(String fname, String lname, String username,
			 String password, String email) throws Exception;
	public void InsertPerson(Person person) throws Exception;
	
	public void UploadImageByLink(Person person, Bookmark bookmark, String url) throws Exception;
	
	public void UploadImageByFile(Person person, Bookmark bookmark, String filePath) throws Exception;

}
