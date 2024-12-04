package com.school.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordDetails {

	    public static void main(String[] args) {
	        String rawPassword = "abcd@1234";

	        // Create an instance of BCryptPasswordEncoder
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	        // Encrypt the password
	        String encodedPassword = passwordEncoder.encode(rawPassword);

	        // Print the encoded password
	        System.out.println("Raw Password: " + rawPassword);
	        System.out.println("Encoded Password: " + encodedPassword);
	    }

}
