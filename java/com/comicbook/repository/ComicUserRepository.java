package com.comicbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicbook.model.ComicUserEnrollment;

public interface ComicUserRepository extends JpaRepository<ComicUserEnrollment, Integer>{

}
