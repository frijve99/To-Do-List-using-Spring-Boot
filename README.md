**Spring Boot Task Management API**

**Overview**

This project delivers a robust and user-friendly REST API for managing tasks using Spring Boot. It boasts comprehensive features, secure authentication, and a well-structured design, making it an ideal solution for streamlining your task organization.

**Features**

**User Registration and Authentication:** 

**POST /api/register:** Register a new user.

**POST /api/login:** User login with JWT authentication.


**Task Management:**

**POST /api/tasks:** Create a new task.

**GET /api/tasks:** Retrieve all tasks for the authenticated user.

**PUT /api/tasks/{id}:** Update a task by ID.

**DELETE /api/tasks/{id}:** Delete a task by ID.


**REST API:**

Follows REST principles with appropriate HTTP methods.

**Data Validation:**

Implements server-side validation for user registration and task creation.

**Database Operations:**

Uses a MySQL databasewith an ORM for CRUD functionality.


**Getting Started**

**Prerequisites:**

Java JDK (version 21)
Maven
MySQL database


Installation:
Clone the repository: 
```bash
git clone https://github.com/frijve99/To-Do-List-using-Spring-Boot.git
```

**REST API Endpoints**

**User Registration**

Endpoint: POST /api/register

Request Payload:
```json

{
  "username": "your_username",
  "email": "your_email@example.com",
  "password": "your_secure_password"
}
```
Response:
```json

{
  "message": "User registered successfully."
}
```

**User Login**

Endpoint: POST /api/login
Request Payload:
```json

{
  "username": "your_username",
  "password": "your_password"
}
```
Response:
```json

{
    "token": "token",
    "userName": "name",
    "email": "userEmail"
}
```

**Creating a Task**

Endpoint: POST /api/tasks

Request Payload:
```json
{
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
Response:
```json
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```

**Getting Tasks**

Endpoint: GET /api/tasks

Response:
```json

[
  {
    "taskId": "task_id",
    "title": "task_title",
    "description": "task_description",
    "status": "task_status"
  },
  {
    "taskId": "task_id",
    "title": "task_title",
    "description": "task_description",
    "status": "task_status"
  }
]
```
**Deleting a Task**

Endpoint: DELETE /api/tasks/{taskId}

Response:
```json
{
  "Confirmation Message": "Task deleted successfully."
}
```

**Updating a Task**

Endpoint: PUT /api/tasks

Request Payload:
```json
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
Response:
```json
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
