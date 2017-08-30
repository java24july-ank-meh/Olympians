package com.olympians.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //change primary key to cname?
	private int cid;
	@Column
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
	public Category(String cname) {
		super();
		this.cname = cname;
	}
	public Category() {
		super();
	}
	@Override
	public String toString() {
		return "Categories [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
}
