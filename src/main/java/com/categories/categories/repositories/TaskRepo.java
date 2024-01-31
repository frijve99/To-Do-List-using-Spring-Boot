package com.categories.categories.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.categories.categories.models.Person;
import com.categories.categories.models.Tasks;


public interface TaskRepo extends JpaRepository<Tasks,Integer>{
	void deleteById(int taskId);
	List<Tasks> findAllByPerson(Person person);
}