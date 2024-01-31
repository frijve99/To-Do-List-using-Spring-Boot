package com.categories.categories.services;

import java.util.*;
import java.util.regex.Pattern;

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
	
	
	public TaskResponse addTask(Tasks task, String userName) {
		Optional<Person> opt= userRepo.findById(userName);
		Person person = opt.get();
		task.setPerson(person);
		taskRepo.save(task);
		TaskResponse tsp = new TaskResponse(task.getTaskId(),task.getTittle(),task.getDescription(),task.getStatus());
		return tsp;
	}
	
	public List<TaskResponse> getTask(String userName){
		Optional<Person> opt= userRepo.findById(userName);
		Person person = opt.get();
		List<Tasks> list = taskRepo.findAllByPerson(person);
		List<TaskResponse> taskList = new ArrayList<>();
		for (Tasks task : list) {
		    TaskResponse tsk = new TaskResponse(task.getTaskId(),task.getTittle(),task.getDescription(),task.getStatus());
		    taskList.add(tsk);
		}
		return taskList;
	}
	
	public ResponseEntity<?> delTask(int taskId){
		taskRepo.deleteById(taskId);
		return ResponseEntity.ok("Task Deleted");
	}
	
	public TaskResponse updateTask(Tasks task,String userName){
		
		Optional<Tasks> opt = taskRepo.findById(task.getTaskId());
		Tasks prevTask = opt.get();
		prevTask.setDescription(task.getDescription());
		prevTask.setStatus(task.getStatus());
		prevTask.setTittle(task.getTittle());
		taskRepo.save(prevTask);
		TaskResponse tsp = new TaskResponse(prevTask.getTaskId(),prevTask.getTittle(),prevTask.getDescription(),prevTask.getStatus());
		return tsp;
	}
	
	
}
