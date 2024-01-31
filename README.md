**Spring Boot Task Management API**

**Overview**

This project delivers a robust and user-friendly REST API for managing tasks using Spring Boot. It boasts comprehensive features, secure authentication, and a well-structured design, making it an ideal solution for streamlining your task organization.

**Features**

User Registration and Authentication: Register new users and securely log in using JWT tokens.

RESTful API Endpoints: Adhere to REST principles for intuitive and consistent interactions.

Task Management: Create, retrieve, update, and delete tasks efficiently.

Data Validation: Ensure data integrity with server-side validation for user registration and task creation.

Database Operations: Leverage a MySQL database with an ORM for seamless CRUD functionality.

Unit Testing: Rigorous unit tests guarantee code quality and reliability.

Authentication and Authorization: JWT-based authentication safeguards user data and enforces access control.

Getting Started

Prerequisites:

Java JDK (version 21)
Maven
MySQL database
Installation:

Clone the repository: git clone https://github.com/your-username/spring-boot-task-management.git
Update application.properties with your database configuration.
REST API Endpoints

User Registration

Endpoint: POST /api/register
Request Payload:

JSON
{
  "username": "your_username",
  "email": "your_email@example.com",
  "password": "your_secure_password"
}
Use code with caution. Learn more
Response:

JSON
{
  "message": "User registered successfully."
}
Use code with caution. Learn more
User Login

Endpoint: POST /api/login
Request Payload:

JSON
{
  "username": "your_username",
  "password": "your_password"
}
Use code with caution. Learn more
Response:

JSON
{
    "token": "token",
    "userName": "name",
    "email": "userEmail"
}
Use code with caution. Learn more
Creating a Task

Endpoint: POST /api/tasks
Request Payload:

JSON
{
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
Use code with caution. Learn more
Response:

JSON
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
Use code with caution. Learn more
Getting Tasks

Endpoint: GET /api/tasks

Response:

JSON
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
Use code with caution. Learn more
Deleting a Task

Endpoint: DELETE /api/tasks/{taskId}

Response:

JSON
{
  "Confirmation Message": "Task deleted successfully."
}
Use code with caution. Learn more
Updating a Task

Endpoint: PUT /api/tasks
Request Payload:

JSON
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
Use code with caution. Learn more
Response:

JSON
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
