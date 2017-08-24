package com.olympians.beans;

public class Categories {

	private int cid;
	private String cname;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Categories(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Categories() {
		super();
	}
	
}
