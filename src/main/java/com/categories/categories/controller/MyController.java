package com.categories.categories.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@PostMapping("/register")
	public String doRegister(@RequestBody Person person) {
		System.out.println("Register Found");
		return userService.addUser(person);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody Person person) {
		return userService.checkUser(person);
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<?> getTask(Authentication authentication, @RequestBody Tasks task) {
		TaskResponse addedTask;
		if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            addedTask = taskService.addTask(task,username);
        } else {
            return ResponseEntity.ok("Error");
        }
		return ResponseEntity.ok(addedTask);
	}
	
	@GetMapping("/tasks")
	public ResponseEntity<?> getTask(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return ResponseEntity.ok(taskService.getTask(username));
        }
        return ResponseEntity.ok("Error");		
	}
	
	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<?> delTask(Authentication authentication, @PathVariable int taskId) {
		if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(taskService.delTask(taskId));
        }
        return ResponseEntity.ok("Error");	
	}
	
	@PutMapping("/tasks")
	public ResponseEntity<?> updateTask(Authentication authentication, @RequestBody Tasks task) {
		TaskResponse addedTask;
		if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            addedTask = taskService.updateTask(task,username);
        } else {
            return ResponseEntity.ok("Error");
        }
		return ResponseEntity.ok(addedTask);
	}
	
	@GetMapping("/check")
	public String checkUser(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return "Logged in user: " + username;
        } else {
            return "User not authenticated";
        }
	}
}
