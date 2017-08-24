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
	
	

	public Person(String fname, String lname, String username, String password, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
