package com.olympians.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@OneToMany
	@JoinColumn(name="pid")
	private List<Bookmark> blist;
	
	

	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Bookmark> getBlist() {
		return blist;
	}


	public void setBlist(List<Bookmark> blist) {
		this.blist = blist;
	}


	public Person(int pid, String fname, String lname, String username, String password, String email,
			List<Bookmark> blist) {
		super();
		this.pid = pid;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.blist = blist;
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", password="
				+ password + ", email=" + email + ", blist=" + blist + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
