package com.sayan.library.LibraryCMSService.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sayan.library.LibraryCMSService.model.Role;
import com.sayan.library.LibraryCMSService.model.User;
import com.sayan.library.LibraryCMSService.repository.RoleRepository;
import com.sayan.library.LibraryCMSService.repository.UserRepository;


@Service
public class UserSecurityService implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(null == user) {
			LOG.warn("Email{} not found", email);
			throw new UsernameNotFoundException("Username" + email + "not found");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
//        user.getAuthorities().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
//        });
		
		Role role = roleRepository.findById(Long.valueOf(user.getRole().getRoleId())).orElse(null);

		authorities.add(new SimpleGrantedAuthority(role.getName()));
		 
		
        System.out.println(authorities);
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
        
        System.out.println("OK");

        return userDetails;
	}
}
