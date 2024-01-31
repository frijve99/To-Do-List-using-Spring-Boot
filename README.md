**Spring Boot Task Management API**

**Overview**

This project delivers a robust and user-friendly REST API for managing tasks using Spring Boot. It boasts comprehensive features, secure authentication, and a well-structured design, making it an ideal solution for streamlining your task organization.

**Features**

**User Registration and Authentication:** Register new users and securely log in using JWT tokens.

**RESTful API Endpoints:** Adhere to REST principles for intuitive and consistent interactions.

**Task Management:** Create, retrieve, update, and delete tasks efficiently.

**Data Validation:** Ensure data integrity with server-side validation for user registration and task creation.

**Database Operations:** Leverage a MySQL database with an ORM for seamless CRUD functionality.

**Authentication and Authorization:** JWT-based authentication safeguards user data and enforces access control.




**Getting Started**

**Prerequisites:**

Java JDK (version 21)
Maven
MySQL database


Installation:
Clone the repository: 
```bash
git clone https://github.com/your-username/spring-boot-task-management.git
```

**REST API Endpoints**

**User Registration**

Endpoint: POST /api/register

Request Payload:
```json
JSON
{
  "username": "your_username",
  "email": "your_email@example.com",
  "password": "your_secure_password"
}
```
Response:
```json
JSON
{
  "message": "User registered successfully."
}
```

**User Login**

Endpoint: POST /api/login
Request Payload:
```json
JSON
{
  "username": "your_username",
  "password": "your_password"
}
```
Response:
```json
JSON
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
JSON
{
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
Response:
```json
JSON
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
```
**Deleting a Task**

Endpoint: DELETE /api/tasks/{taskId}

Response:
```json
JSON
{
  "Confirmation Message": "Task deleted successfully."
}
```

**Updating a Task**

Endpoint: PUT /api/tasks

Request Payload:
```json
JSON
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
Response:
```json
JSON
{
  "taskId": "task_id",
  "title": "task_title",
  "description": "task_description",
  "status": "task_status"
}
```
