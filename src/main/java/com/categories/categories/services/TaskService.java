package com.categories.categories.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.categories.categories.models.Tasks;
import com.categories.categories.repositories.TaskRepo;

@Service
public class TaskService {
	@Autowired
	TaskRepo taskRepo;
	
	public Tasks addTask(Tasks task) {
		return taskRepo.save(task);
	}
	
	public List<Tasks> getTask(){
		return taskRepo.findAll();
	}
	
	public List<Tasks> delTask(int taskId){
		taskRepo.deleteById(taskId);
		
		return taskRepo.findAll();
	}
	
	public List<Tasks> updateTask(Tasks task){
		
//		taskRepo.save(task);
		return taskRepo.findAll();
	}
}
