package com.sayan.library.LibraryCMSService.service;

import org.springframework.stereotype.Service;

import com.sayan.library.LibraryCMSService.model.User;
import com.sayan.library.LibraryCMSService.model.UserProfile;

@Service
public interface UserService {
	User createUser(User user, UserProfile userProfile);
}
