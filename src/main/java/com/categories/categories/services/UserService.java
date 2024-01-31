package com.categories.categories.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.categories.categories.models.Person;
import com.categories.categories.repositories.UserRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService{
	@Autowired
	UserRepo userRepo;

	public UserService() {
		
	}
	
	public String checkUser(Person checkedPerson) {
		Optional<Person> opt = userRepo.findById(checkedPerson.getUserName());
		Person person = opt.get();
		System.out.println(person.getUserName());
		boolean userExist = hashPassword(checkedPerson.getPassword()).equals(person.getPassword());
		if(userExist) return generateJwtToken(checkedPerson.getUserName());
		return "Inavlid Credential";
	}
	
	public String generateJwtToken(String username) {
	    String secretKey = "skjdshebrehgyerber123456890edrertet"; // Replace with your secret key
	    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

	    return Jwts.builder()
	            .setSubject(username)
	            .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
	            .compact();
	}
	
	public String addUser(Person person) {
		boolean userExist = userRepo.existsById(person.getUserName());
		if(userExist) return "User Already Exist";
		
		
		String hashedPassword = hashPassword(person.getPassword());

		   // Set the hashed password back to the Person object
		person.setPassword(hashedPassword);
	    userRepo.save(person);
		return "Registered";
	}
	
	
	public String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashedBytes = md.digest(password.getBytes());

	        // Convert the byte array to a Base64 encoded string
	        String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

	        return hashedPassword;
	    } catch (NoSuchAlgorithmException e) {
	        // Handle exception, e.g., log it or throw a custom exception
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	
}
