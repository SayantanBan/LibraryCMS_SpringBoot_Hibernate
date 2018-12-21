package com.sayan.library.LibraryCMSService.repository;

import org.springframework.data.repository.CrudRepository;

import com.sayan.library.LibraryCMSService.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
