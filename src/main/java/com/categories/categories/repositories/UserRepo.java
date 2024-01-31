package com.categories.categories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.categories.categories.models.Person;

public interface UserRepo extends JpaRepository<Person,String>{
	
}
