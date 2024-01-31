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
	private String secretKey = "skjdshebrehgyerber123456890edrertet";
	@Autowired
	UserRepo userRepo;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		System.out.println("Filtering Found");
		try {
	        String jwtToken = extractJwtToken(request);
	        String userName="";
	        if (jwtToken != null) {
	             	userName = Jwts.parserBuilder()
	                    .setSigningKey(secretKey.getBytes())
	                    .build()
	                    .parseClaimsJws(jwtToken)
	                    .getBody()
	                    .getSubject();
	        
			System.out.println(userName);
	            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                // Load user details from the database using UserDetailsService
	                boolean userExist = userRepo.existsById(userName);
	                
	                if(userExist) {
	                	 // Create Authentication object
		            	Authentication authentication = new UsernamePasswordAuthenticationToken(
		                        userName, null, Collections.emptyList());
		            	
		                // Set the Authentication object in the SecurityContext
		                SecurityContextHolder.getContext().setAuthentication(authentication);
		                System.out.println("Authenticated");
	                }
	                else {
	                	System.out.println("Not Authenticated");
	                }
	               
	            }
	        }
	    } catch (Exception e) {
	        // Handle expired token exception
	    	System.out.println(e);
	        return;
	    }

	    filterChain.doFilter(request, response);

	}
	
	private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7); // Remove "Bearer " prefix
//        }

        return bearerToken;
    }
}
