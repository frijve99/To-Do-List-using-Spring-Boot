package com.categories.categories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
	    http.authorizeHttpRequests(rQ -> {
	           rQ.requestMatchers("/register","/login").permitAll()
	           .anyRequest().authenticated();
	         });
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean
    public OncePerRequestFilter authenticationFilter() {
        return new AuthenticationFilter();
    }
	
	
	
}


