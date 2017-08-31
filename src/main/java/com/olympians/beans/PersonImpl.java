package com.olympians.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
@Repository
@Entity
@Table(name="PERSON")
@JsonIgnoreProperties("blist")
public class PersonImpl implements Person{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column(unique=true)
	private String username;
	@Column
	private String pword;
	@Column
	private String email;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
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
		return pword;
	}


	public void setPassword(String password) {
		this.pword = password;
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

	public PersonImpl(String fname, String lname, String username, String pword, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.pword = pword;
		this.email = email;
	}

	public PersonImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	//copy constructor
		public PersonImpl(Person p) {
			this.pid = p.getPid();
			this.lname = p.getLname();
			this.username = p.getUsername();
			this.pword = p.getPassword();
			this.email = p.getEmail();
			this.blist = p.getBlist();
			this.fname = p.getFname();
		}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", password=" + pword + "]";
	}
	
}
