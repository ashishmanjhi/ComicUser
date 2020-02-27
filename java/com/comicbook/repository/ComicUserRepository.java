package com.comicbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicbook.model.ComicUserEnrollment;

/**
 * @author Ashish.manjhi
 * 
 *This {@link ComicUserRepository} interface is the Repository of {@link ComicUserEnrollment} class.
 */
public interface ComicUserRepository extends JpaRepository<ComicUserEnrollment, Integer>{

}
