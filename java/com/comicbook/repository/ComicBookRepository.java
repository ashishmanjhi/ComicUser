package com.comicbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicbook.model.ComicBook;

/**
 * @author Ashish.manjhi
 *
 *
 *This {@link ComicBookRepository} interface is the Repository of {@link ComicBook} class.
 */
public interface ComicBookRepository extends JpaRepository<ComicBook, Integer> {

	/**These are some custom made RESTAPIs method used for performing 
	 * different search operation on book database.*/
	
	// Find out the comic book with the inputed read time 
	Optional<ComicBook> findByReadTime(String time);

}
