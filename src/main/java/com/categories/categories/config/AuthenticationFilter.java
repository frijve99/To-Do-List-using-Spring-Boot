package com.categories.categories.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.categories.categories.repositories.UserRepo;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

public class AuthenticationFilter extends OncePerRequestFilter{
	//This is a secret Key to both Generate or validate JWT token.
	private String secretKey = "skjdshebrehgyerber123456890edrertet";
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		System.out.println("Filtering Found");
		try {
			//Extracting the Token..
	        String jwtToken = extractJwtToken(request);
	        String userName="";
	        
	        //Getting the userName from the Token using Secret Key..
	        if (jwtToken != null) {
	             	userName = Jwts.parserBuilder()
	                    .setSigningKey(secretKey.getBytes())
	                    .build()
	                    .parseClaimsJws(jwtToken)
	                    .getBody()
	                    .getSubject();
	        
			System.out.println(userName);
			
	        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        	
	        	
	                // Verifying if the user exist in the database..
	                boolean userExist = userRepo.existsById(userName);
	                
	                if(userExist) {
	                	 // Creating Authentication object
		            	Authentication authentication = new UsernamePasswordAuthenticationToken(
		                        userName, null, Collections.emptyList());
		            	
		                // Setting the Authentication object in the SecurityContext
		                SecurityContextHolder.getContext().setAuthentication(authentication);
		                System.out.println("Authenticated");
	                }
	                else {
	                	System.out.println("Not Authenticated");
	                }
	               
	            }
	        }
	    } catch (Exception e) {
	        // Handling expired token exception
	    	System.out.println(e);
	        return;
	    }

	    filterChain.doFilter(request, response);

	}
	
	private String extractJwtToken(HttpServletRequest request) {
		//Getting the Token..
        String Token = request.getHeader("Authorization");
        return Token;
    }
}
