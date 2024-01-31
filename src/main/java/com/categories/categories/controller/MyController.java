package com.categories.categories.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String doLogin(@RequestBody Person person) {
		return userService.checkUser(person);
	}
	
	@PostMapping("/tasks/add")
	public Tasks getTask(@RequestBody Tasks task) {
		return taskService.addTask(task);
	}
	
	@GetMapping("/tasks/getTasks")
	public List<Tasks> getTask() {
		return taskService.getTask();
	}
	
	@DeleteMapping("/tasks/{taskId}")
	public List<Tasks> delTask(@PathVariable int taskId) {
		return taskService.delTask(taskId);
	}
	
	@PutMapping("/tasks/update")
	public List<Tasks> updateTask(@RequestBody Tasks task) {
		return taskService.updateTask(task);
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
