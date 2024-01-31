# Spring Boot Task Management API

## Features

### User Registration and Authentication
**POST /api/register:** Register a new user.
**POST /api/login:** User login with JWT authentication.

### Task Management
**POST /api/tasks:** Create a new task.
**GET /api/tasks:** Retrieve all tasks for the authenticated user.
**PUT /api/tasks/{id}:** Update a task by ID.
**DELETE /api/tasks/{id}:** Delete a task by ID.


###REST API:**
Follows REST principles with appropriate HTTP methods.

###Data Validation:**
Implements server-side validation for user registration and task creation.

###Database Operations:**
Uses a MySQL database with an ORM for CRUD functionality.


###uthentication and Authorization:**
Uses JWT for user authentication.


###Getting Started**
**Prerequisites**
Java JDK (version 21)
Maven
Databases:  MySQL


###

Clone the repository: git clone https://github.com/your-username/spring-boot-task-management.git

Update application.properties with your database configuration.


### User Registration and Authentication

#### Register a new user
**POST /api/register:**
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
