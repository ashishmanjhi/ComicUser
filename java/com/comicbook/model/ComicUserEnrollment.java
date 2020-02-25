package com.comicbook.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="COMIC_USERS")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class ComicUserEnrollment{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMIC_USER_ID")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMICBOOK_ID")
	private ComicBook comicBook;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "REGISTERED_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate= new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ComicBook getComicBook() {
		return comicBook;
	}

	
	public void setComicBook(ComicBook comicBook) {
		this.comicBook = comicBook;
	}

	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


}
