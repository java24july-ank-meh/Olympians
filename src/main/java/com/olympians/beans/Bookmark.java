package com.olympians.beans;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bmid;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@ManyToOne
	@JoinColumn(name = "pid")
	private PersonImpl person;
	@Column
	private int rating;
	@OneToOne
	@JoinColumn(name = "cid")
	private Category category;
	@Column
	private String image;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dateAdded;  
	
	@Column
	private String imageDeleteHash; // needed for imgur delete

	public String getImageDeleteHash() {
		return imageDeleteHash;
	}

	public void setImageDeleteHash(String imageDeleteHash) {
		this.imageDeleteHash = imageDeleteHash;
	}
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(PersonImpl person) {
		this.person = person;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}


	@Override
	public String toString() {
		return "Bookmark [bmid=" + bmid + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", person=" + person + ", rating=" + rating + ", category=" + category + ", image=" + image + "]";
	}

	public Bookmark(String name, String address, String description, PersonImpl person, int rating, Category category,
			String image) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.person = person;
		this.rating = rating;
		this.category = category;
		this.image = image;
	}

	public Bookmark() {
		super();
		// TODO Auto-generated constructor stub
	}
	//cat name date rating
	// comparator to order by category
	public static class SortByCategory implements Comparator<Bookmark> {
		@Override
		public int compare(Bookmark b1, Bookmark b2) {
			return b1.getCategory().getCname().compareTo(b2.getCategory().getCname());
		}
	}

	// comparator to order by name
	public static class SortByName implements Comparator<Bookmark> {
		@Override
		public int compare(Bookmark b1, Bookmark b2) {
			return b1.getName().compareTo(b2.getName());
		}
	}
	
	// comparator to order by date
	public static class SortByDate implements Comparator<Bookmark> {
		@Override
		public int compare(Bookmark b1, Bookmark b2) {
			return b1.getDateAdded().compareTo(b2.getDateAdded());
		}
	}
	
	
	// comparator to order by rating
	public static class SortByRating implements Comparator<Bookmark> {
		@Override
		public int compare(Bookmark b1, Bookmark b2) {
			return Integer.compare(b1.getRating(), b2.getRating());
		}
	}

	
}