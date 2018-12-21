package com.sayan.library.LibraryCMSService.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.library.LibraryCMSService.model.Role;
import com.sayan.library.LibraryCMSService.model.User;
import com.sayan.library.LibraryCMSService.model.UserProfile;
import com.sayan.library.LibraryCMSService.repository.RoleRepository;
import com.sayan.library.LibraryCMSService.repository.UserRepository;
import com.sayan.library.LibraryCMSService.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public User createUser(User user, UserProfile userProfile) {
		User localUser = userRepository.findByEmail(user.getEmail());
		
		Role role = roleRepository.findById(Long.valueOf(2)).orElse(null);
		
		if(localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getEmail());
		}
		else {
			
	        // Set child reference(userProfile) in parent entity(user)
	        user.setUserProfile(userProfile);

	        // Set parent reference(user) in child entity(userProfile)
	        userProfile.setUser(user);
	        
	        user.setRole(role);

	        // Save Parent Reference (which will save the child as well)
			localUser = userRepository.save(user);

		}
		
		return localUser;
	}


}
