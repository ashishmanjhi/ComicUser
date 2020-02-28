package com.comicbook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Ashish.manjhi
 * 
 *This {@link Users} class represents detail of the Book.
 *
 */
@Entity
@Table(name="USERS")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "id")
public class User {

	// User Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;

	// User Name
	@NotNull
	@Column(name="username")
	private String username;

	// User Password
	@NotNull
	@Column(name="password")
	private String password;

	// User Country
	@NotNull
	@Column(name="country")
	private String country;

	//// User Email
	@NotNull
	@Column(name="email")
	private String email;

	// User Age
	@NotNull
	@Column(name="age")
	private int age;

	// User Sex
	@NotNull
	@Column(name="sex")
	private String sex;

	// User Created Date
	@Column(name = "created_at")
	private Date createdAt= new Date();

	// ComicUser table.
	@OneToMany(mappedBy = "user")
	private Set<ComicUserEnrollment> comicUserEnrollments= new HashSet<ComicUserEnrollment>();


	// ----------------
	// - CONSTRUCTORS -
	// ----------------

	/**
	 * Default Constructor for User
	 */
	public User() {
	}

	/**
	 * Constructor for User
	 * 
	 * @param username
	 * @param password
	 * @param country
	 * @param email
	 * @param age
	 * @param sex
	 */
	public User(@NotNull String username, @NotNull String password, @NotNull String country, @NotNull String email,
			@NotNull int age, @NotNull String sex) {
		super();
		this.username = username;
		this.password = password;
		this.country = country;
		this.email = email;
		this.age = age;
		this.sex = sex;
	}

	/**
	 * @param comic
	 */
	public void addComicBook(ComicUserEnrollment comic) {
		this.comicUserEnrollments.add(comic);
	}


	/**
	 * @return User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param User Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return User Country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param User country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return User email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param User email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return User age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param User age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return User sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param User sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return User created date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param User created date
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return User name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param User name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return User password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param User password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return comicUserEnrollments
	 */
	public Set<ComicUserEnrollment> getComicUserEnrollments() {
		return comicUserEnrollments;
	}

	/**
	 * @param comicUserEnrollments
	 */
	public void setComicUserEnrollments(Set<ComicUserEnrollment> comicUserEnrollments) {
		this.comicUserEnrollments = comicUserEnrollments;
	}

	/**
	 * @param comicUserEnrollment
	 */
	public void addComicUserEnrollment(ComicUserEnrollment comicUserEnrollment) {
		this.comicUserEnrollments.add(comicUserEnrollment);
	}

}
