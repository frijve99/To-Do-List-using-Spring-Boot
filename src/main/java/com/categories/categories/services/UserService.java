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
	
	//It will check  authenticate the User.
	public ResponseEntity<?> checkUser(Person checkedPerson) {
		
		//Getting Given UserInfo..
		Optional<Person> opt = userRepo.findById(checkedPerson.getUserName());
		if(!opt.isEmpty()) {
			Person person = opt.get();
			
			System.out.println(person.getUserName());
			
			//Checking if the Credential matches With the DB.
			boolean userExist = hashPassword(checkedPerson.getPassword()).equals(person.getPassword());
			if(userExist) {
				//A token will be generated for the logged user..
				String Token = generateJwtToken(checkedPerson.getUserName());
				
				//Sending Token info as well as user..
				UserResponse usp = new UserResponse(person.getUserName(),person.getEmail(),Token);
				return ResponseEntity.ok(usp);
			}
			return ResponseEntity.ok("Invalid Credential");
		}
		
		return ResponseEntity.ok("Invalid Credential");
	}
	
	
	// It will generate a Token based on userName and secretKey
	public String generateJwtToken(String username) {
	    String secretKey = "skjdshebrehgyerber123456890edrertet";
	    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

	    return Jwts.builder()
	            .setSubject(username)
	            .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
	            .compact();
	}
	
	
	//It will Register User and Save into the dataBase..
	
	public String addUser(Person person) {
		boolean userExist = userRepo.existsById(person.getUserName());
		if(userExist) return "UserName Already Exist";
		
		boolean isEmailValid = isValidEmail(person.getEmail());
		if(!isEmailValid) return "Invalid Email";
		
		boolean isPassStrong = isStrongPass(person.getPassword());
		if(!isPassStrong) return "Choose a more Strong Password";
		
		String hashedPassword = hashPassword(person.getPassword());

		person.setPassword(hashedPassword);
		
		//Saving into the Db
	    userRepo.save(person);
		return "User Registered";
	}
	
	// It will convert Password into HashPASs..
	public String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashedBytes = md.digest(password.getBytes());

	        
	        String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

	        return hashedPassword;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	//It will Check Email Validity
	public  boolean isValidEmail(String email) {
		String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
        	      .matcher(email)
        	      .matches();
    }
	
	//It will Check Password Validity
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
