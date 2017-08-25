package com.olympians.beans;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Bookmark {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bmid;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@ManyToOne
	@JoinColumn(name="pid")
	private Person person;
	@Column
	private int rating;
	@OneToOne
	@JoinColumn(name="cid")
	private Category category;
	@Column
	private Blob image;
	
	
	public Bookmark(int bmid, String name, String address, String description) {
		super();
		this.bmid = bmid;
		this.name = name;
		this.address = address;
		this.description = description;
	}
	
	public Bookmark() {
		super();
		// TODO Auto-generated constructor stub
	}
}