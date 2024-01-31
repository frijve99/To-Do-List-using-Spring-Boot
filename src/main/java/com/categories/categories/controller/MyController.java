package com.categories.categories.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.categories.categories.models.Person;
import com.categories.categories.models.Tasks;
import com.categories.categories.returnresponse.TaskResponse;
import com.categories.categories.services.TaskService;
import com.categories.categories.services.UserService;



@RestController
public class MyController {
	@Autowired
	UserService userService;
	@Autowired
	TaskService taskService;
	
	//This is the API for Register.. The Path is "localhost:8080/register"
	//It will return a verification Message also warning about invalid input.
	@PostMapping("/register")
	public ResponseEntity<?> doRegister(@RequestBody Person person) {
		try {
	        System.out.println("Register Found");
	        String result = userService.addUser(person);
	        return ResponseEntity.ok(result);
	    } 
		catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
	
	//This is the API for Register.. The Path is "localhost:8080/login"
	//It will return user information as well as a Token which will be used for Authentication later.
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody Person person) {
		try {
	        return userService.checkUser(person);
	    } 
		catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
	
	
	//This is the API for Creating Task..
	//To access this it will need Token as given while login..
	//It will return created task information.
	@PostMapping("/tasks")
	public ResponseEntity<?> getTask(Authentication authentication, @RequestBody Tasks task) {
		TaskResponse addedTask;
	    try {
	        if (authentication != null && authentication.isAuthenticated()) {
	            String username = authentication.getName();
	            addedTask = taskService.addTask(task, username);
	            return ResponseEntity.ok(addedTask);
	        } 
	        else {
	        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
	        }
	    } 
	    catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
	
	//This is the API for getting the all tasks for a user..
	//To access this it will need Token as given while login..
	//It will return all tasks of a user.
	@GetMapping("/tasks")
	public ResponseEntity<?> getTask(Authentication authentication) {
		try {
	        if (authentication != null && authentication.isAuthenticated()) {
	            String username = authentication.getName();
	            return ResponseEntity.ok(taskService.getTask(username));
	        } 
	        else {
	        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
	        }
	    } 
		catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }		
	}
	
	
	//This is the API for deleting the a task for a user..
	//To access this it will need Token as given while login..
	//It will return confirmation if the task has been deleted.
	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<?> delTask(Authentication authentication, @PathVariable int taskId) {
		try {
	        if (authentication != null && authentication.isAuthenticated()) {
	            return ResponseEntity.ok(taskService.delTask(taskId));
	        } 
	        else {
	        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
	        }
	    } 
		catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
	
	//This is the API for Updating a task..
	//To access this it will need Token as given while login..
	//It will return Updated task information.
	@PutMapping("/tasks")
	public ResponseEntity<?> updateTask(Authentication authentication, @RequestBody Tasks task) {
		TaskResponse addedTask;
	    try {
	        if (authentication != null && authentication.isAuthenticated()) {
	            String username = authentication.getName();
	            addedTask = taskService.updateTask(task, username);
	            return ResponseEntity.ok(addedTask);
	        } 
	        else {
	        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
	        }
	    } 
	    catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
	
}
