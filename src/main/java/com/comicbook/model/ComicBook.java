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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Ashish.manjhi
 * 
 *This {@link ComicBook} class represents detail of the Book.
 *
 */
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

	// ComicBook ReadTime
	@NotNull
	@Column(name="readTime")
	private String readTime;

	// ComicBook description
	@Column(name="description")
	private String description;

	// ComicBook Date
	@NotNull
	@Column(name = "created_at")
	private Date createdAt= new Date();
	
	// Join table
	@OneToMany(mappedBy = "comicBook")
	private Set<ComicUserEnrollment> comicUserEnrollments= new HashSet<ComicUserEnrollment>();
	
	
	// ----------------
    // - CONSTRUCTORS -
    // ----------------

	/**
	 * Default Constructor for ComicBook
	 */
	public ComicBook() {
		
	}

	/**
	 *  Constructor for ComicBook
	 * 
	 * @param title
	 * @param writer
	 * @param publisher
	 * @param genre
	 * @param readTime
	 * @param description
	 * @param createdAt
	 * @param comicUserEnrollments
	 */
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
	
	/**
	 * @return comic book id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param comic book id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return comic book title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param comic book title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return comic book writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param comic book writer
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return comic book publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param comic book publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return comic book genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param comic book genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return comic book readTime 
	 */
	public String getReadTime() {
		return readTime;
	}

	/**
	 * @param comic book readTime
	 */
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	/**
	 * @return comic book description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param comic book description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return comic book created Date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param comic book created Date
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
