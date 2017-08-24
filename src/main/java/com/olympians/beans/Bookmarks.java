package com.olympians.beans;

import java.sql.Blob;

public class Bookmarks {

	private int bmid;
	private String name;
	private String address;
	private String description;
	private int uid;
	private int rating;
	private  int cid; //category Id
	private Blob image;
	public int getBmid() {
		return bmid;
	}
	public void setBmid(int bmid) {
		this.bmid = bmid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Bookmarks(int bmid, String name, String address, String description, int uid, int rating, int cid,
			Blob image) {
		super();
		this.bmid = bmid;
		this.name = name;
		this.address = address;
		this.description = description;
		this.uid = uid;
		this.rating = rating;
		this.cid = cid;
		this.image = image;
	}
	public Bookmarks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}