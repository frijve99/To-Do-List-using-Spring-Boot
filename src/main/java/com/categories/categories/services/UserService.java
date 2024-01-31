package com.categories.categories.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.categories.categories.models.Person;
import com.categories.categories.repositories.UserRepo;
import com.categories.categories.returnresponse.UserResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService{
	@Autowired
	UserRepo userRepo;

	public UserService() {
		
	}
	
	public ResponseEntity<?> checkUser(Person checkedPerson) {
		Optional<Person> opt = userRepo.findById(checkedPerson.getUserName());
		Person person = opt.get();
		System.out.println(person.getUserName());
		boolean userExist = hashPassword(checkedPerson.getPassword()).equals(person.getPassword());
		if(userExist) {
			String Token = generateJwtToken(checkedPerson.getUserName());
			UserResponse usp = new UserResponse(person.getUserName(),person.getEmail(),Token);
			return ResponseEntity.ok(usp);
		}
		return ResponseEntity.ok("Invalid Credential");
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
		if(userExist) return "UserName Already Exist";
		
		boolean isEmailValid = isValidEmail(person.getEmail());
		if(!isEmailValid) return "Invalid Email";
		
		boolean isPassStrong = isStrongPass(person.getPassword());
		if(!isPassStrong) return "Choose a more Strong Password";
		
		String hashedPassword = hashPassword(person.getPassword());

		// Set the hashed password back to the Person object
		person.setPassword(hashedPassword);
	    userRepo.save(person);
		return "User Registered";
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
	
	public  boolean isValidEmail(String email) {
		String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
        	      .matcher(email)
        	      .matches();
    }
	
	public boolean isStrongPass(String pass) {
		boolean hasUpperLetter=false,hasLowerLetter=false,hasNumber=false;
		if(pass.length()<8) return false;
		for(int i=0 ; i<pass.length(); i++) {
			if(pass.charAt(i)>='a' && pass.charAt(i)<='z') hasLowerLetter=true;
			else if(pass.charAt(i)>='A' && pass.charAt(i)<='Z') hasUpperLetter=true;
			else if(pass.charAt(i)>='0' && pass.charAt(i)<='9') hasNumber=true;
		}
		if(!hasLowerLetter || !hasUpperLetter || !hasNumber) return false;
		return true;
	}
	
	
	
}
