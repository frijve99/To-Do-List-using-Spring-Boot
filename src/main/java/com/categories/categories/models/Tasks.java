package com.categories.categories.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tasks {
	@Id
	@GeneratedValue
	private int taskId;
	
	@ManyToOne
    private Person person;
	private String tittle;
	private String description;
	private String status;
	
	
	
	public Tasks(int taskId, Person user, String tittle, String description, String status) {
		super();
		this.taskId = taskId;
		this.person = null;
		this.tittle = tittle;
		this.description = description;
		this.status = status;
	}
	
	public Tasks() {
		
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
