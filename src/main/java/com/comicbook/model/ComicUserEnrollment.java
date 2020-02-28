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

/**
 * @author Ashish.manjhi
 * 
 *This {@link ComicUserEnrollment} class represents detail of the relationship between comic book and users.
 *
 */
@Entity
@Table(name="COMIC_USERS")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class ComicUserEnrollment{

	// Comic User table Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMIC_USER_ID")
	private int id;

	// Comic Book
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMICBOOK_ID")
	private ComicBook comicBook;

	// User
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	// Comic User table Registration Date
	@Column(name = "REGISTERED_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate= new Date();

	// ----------------
    // - CONSTRUCTORS -
    // ----------------

	/**
	 * Default Constructor for Comic User table
	 */
	public ComicUserEnrollment() {
	}
	

	/**
	 * Constructor for Comic User table
	 * 
	 * @param id
	 * @param comicBook
	 * @param user
	 * @param regDate
	 */
	public ComicUserEnrollment(int id, ComicBook comicBook, User user, Date regDate) {
		
		this.id = id;
		this.comicBook = comicBook;
		this.user = user;
		this.regDate = regDate;
	}
	
	

	/**
	 * @return Comic User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param Comic User Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Comic Book
	 */
	public ComicBook getComicBook() {
		return comicBook;
	}

	
	/**
	 * @param comicBook
	 */
	public void setComicBook(ComicBook comicBook) {
		this.comicBook = comicBook;
	}

	/**
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	
	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Comic User registration date
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param Comic User registration date
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


}
