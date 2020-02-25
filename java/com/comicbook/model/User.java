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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="USERS")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;

	@NotNull
	@Column(name="username")
	private String username;
	
	@NotNull
	@Column(name="password")
	private String password;

	@NotNull
	@Column(name="country")
	private String country;

	@NotNull
	@Column(name="email")
	private String email;

	@NotNull
	@Column(name="age")
	private int age;


	@NotNull
	@Column(name="sex")
	private String sex;

	@Column(name = "created_at")
	private Date createdAt= new Date();

	@OneToMany(mappedBy = "user")
	private Set<ComicUserEnrollment> comicUserEnrollments= new HashSet<ComicUserEnrollment>();
	
	

	public User() {
	}

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

	 public void addComicBook(ComicUserEnrollment comic) {
	        this.comicUserEnrollments.add(comic);
	    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Set<ComicUserEnrollment> getComicUserEnrollments() {
		return comicUserEnrollments;
	}

	public void setComicUserEnrollments(Set<ComicUserEnrollment> comicUserEnrollments) {
		this.comicUserEnrollments = comicUserEnrollments;
	}

	
	public void addComicUserEnrollment(ComicUserEnrollment comicUserEnrollment) {
		this.comicUserEnrollments.add(comicUserEnrollment);
	}
	
}
