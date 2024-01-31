package com.categories.categories.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.categories.categories.models.Person;
import com.categories.categories.models.Tasks;
import com.categories.categories.repositories.TaskRepo;
import com.categories.categories.repositories.UserRepo;
import com.categories.categories.returnresponse.TaskResponse;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo taskRepo;
	@Autowired
	UserRepo userRepo;
	
	//It will addTask to The database and return The created task information
	public TaskResponse addTask(Tasks task, String userName) {
		
		//Getting the User Details.
		Optional<Person> opt= userRepo.findById(userName);
		Person person = opt.get();
		
		//Setting the info about user(Person) in Task which is a foreign key
		task.setPerson(person);
		
		//Saving the task into DB
		taskRepo.save(task);
		
		// Creating a TaskResponse of the created Task and returning..
		TaskResponse tsp = new TaskResponse(task.getTaskId(),task.getTittle(),task.getDescription(),task.getStatus());
		return tsp;
	}
	
	//It will get all tasks of the authenticated User from The database and return Them
	public List<TaskResponse> getTask(String userName){
		//Getting the User Details.
		Optional<Person> opt= userRepo.findById(userName);
		Person person = opt.get();
		
		// Getting info about all the tasks of the User..
		List<Tasks> list = taskRepo.findAllByPerson(person);
		
		// Creating a TaskResponse of the created Task and returning..
		List<TaskResponse> taskList = new ArrayList<>();
		for (Tasks task : list) {
		    TaskResponse tsk = new TaskResponse(task.getTaskId(),task.getTittle(),task.getDescription(),task.getStatus());
		    taskList.add(tsk);
		}
		return taskList;
	}
	
	
	//It will delete the Task from The database and return about Confirmation..
	public ResponseEntity<?> delTask(int taskId){
		taskRepo.deleteById(taskId);
		return ResponseEntity.ok("Task Deleted");
	}
	
	
	//It will Update a Task and return it..
	public TaskResponse updateTask(Tasks task,String userName){
		//Getting The Old Task of the same Id.. 
		Optional<Tasks> opt = taskRepo.findById(task.getTaskId());
		Tasks prevTask = opt.get();
		
		//Updating the Task info..
		prevTask.setDescription(task.getDescription());
		prevTask.setStatus(task.getStatus());
		prevTask.setTittle(task.getTittle());
		taskRepo.save(prevTask);
		
//		Creating a TaskResponse of the updated Task and returning..
		TaskResponse tsp = new TaskResponse(prevTask.getTaskId(),prevTask.getTittle(),prevTask.getDescription(),prevTask.getStatus());
		return tsp;
	}
	
	
}
