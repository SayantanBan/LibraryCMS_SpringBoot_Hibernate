package com.sayan.library.LibraryCMSService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sayan.library.LibraryCMSService.model.Book;


public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitleContaining(String keyword);
}
