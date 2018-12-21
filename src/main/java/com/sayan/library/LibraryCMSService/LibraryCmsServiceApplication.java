package com.sayan.library.LibraryCMSService;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sayan.library.LibraryCMSService.model.Gender;
import com.sayan.library.LibraryCMSService.model.User;
import com.sayan.library.LibraryCMSService.model.UserProfile;
import com.sayan.library.LibraryCMSService.repository.UserProfileRepository;
import com.sayan.library.LibraryCMSService.repository.UserRepository;
import com.sayan.library.LibraryCMSService.security.SecurityUtility;
import com.sayan.library.LibraryCMSService.service.UserService;

@SpringBootApplication
public class LibraryCmsServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LibraryCmsServiceApplication.class, args);
	}

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Autowired
    private UserService userService;

    
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String password = SecurityUtility.passwordEncoder().encode("password1$");

        // Create a User instance
        User user = new User("Rajeev", "Singh", "rajeev@callicoder.com",
        		password);
 
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE, dateOfBirth.getTime(),
                "747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore", "Karnataka", "India", "560008");
                
        userService.createUser(user, userProfile);
	}

}

