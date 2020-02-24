package com.comicbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicbook.model.ComicBook;

/**
 * @author Ashish.manjhi
 *
 *
 *This {@link ComicBookRepository} interface is the Repository of {@link ComicBookController} class.
 */
public interface ComicBookRepository extends JpaRepository<ComicBook, Integer> {

}
