package com.categories.categories.returnresponse;

public class TaskResponse {
	private int taskId;
    private String title;
    private String description;
    private String status;
    
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public TaskResponse(int taskId, String title, String description, String status) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.status = status;
	}
    
    
}
