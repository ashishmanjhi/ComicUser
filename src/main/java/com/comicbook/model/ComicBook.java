package com.comicbook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "COMICBOOKS")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class ComicBook {

	// ComicBook Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMICBOOK_ID")
	private int id;

	// ComicBook Title
	@NotNull
	@Column(name="title")
	private String title;

	// ComicBook Writer
	@NotNull
	@Column(name="writer")
	private String writer;

	// ComicBook Publisher
	@NotNull
	@Column(name="publisher")
	private String publisher;

	// ComicBook Genre
	@NotNull
	@Column(name="genre")
	private String genre;

	@NotNull
	@Column(name="readTime")
	private String readTime;

	@Column(name="description")
	private String description;

	@NotNull
	@Column(name = "created_at")
	private Date createdAt= new Date();
	
	@OneToMany(mappedBy = "comicBook")
	private Set<ComicUserEnrollment> comicUserEnrollments= new HashSet<ComicUserEnrollment>();
	
	
	public ComicBook() {
		
	}

	public ComicBook(@NotNull String title, @NotNull String writer, @NotNull String publisher, @NotNull String genre,
			@NotNull String readTime, @NotNull String description, @NotNull Date createdAt,
			List<ComicUserEnrollment> comicUserEnrollments) {
		
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.genre = genre;
		this.readTime = readTime;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getReadTime() {
		return readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
