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

Unit Testing:
Includes unit tests for critical functions/methods.


**Authentication and Authorization:**
Uses JWT for user authentication.


**Getting Started**
Prerequisites
Java JDK (version 21)
Maven
Databases:  MySQL


**Installation**

Clone the repository: git clone https://github.com/your-username/spring-boot-task-management.git

Update application.properties with your database configuration.


**REST API Endpoints**
**User Registration**
Endpoint: POST /api/register
Request Payload:
{
  "username": "your_username",
  "email": "your_email@example.com",
  "password": "your_secure_password"
}

Response:
{
  "message": "User registered successfully."
}

**User Login**
Endpoint: POST /api/login
Request Payload:
{
  "username": "your_username",
  "password": "your_password"
}

Response:
{
    "token": "token",
    "userName": "name",
    "email": "userEmail"
}

**Creating A Task**
EndPoint: POST /api/tasks
Request Payload:
{
  "tittle": "task_tittle",
  "description": "task_description",
  "status": "task_status"
}

Response:
{
  "taskId": "task_id"
  "tittle": "task_tittle",
  "description": "task_description",
  "status": "task_status"
}

**Getting Tasks**
EndPoint: GET /api/tasks
Request Payload:
{
  
}

Response:
[
  {
    "taskId": "task_id"
    "tittle": "task_tittle",
    "description": "task_description",
    "status": "task_status"
  },
  {
    "taskId": "task_id"
    "tittle": "task_tittle",
    "description": "task_description",
    "status": "task_status"
  }
]


**Deleting A Task**
EndPoint: DELETE /api/tasks/{taskId}
Request Payload:
{
  
}

Response:
{
  "Confirmation Messege"
}

**Updatingg A Task**
EndPoint: PUT /api/tasks
Request Payload:
{
  "taskId": "task_id"
  "tittle": "task_tittle",
  "description": "task_description",
  "status": "task_status"
}

Response:
{
  "taskId": "task_id"
  "tittle": "task_tittle",
  "description": "task_description",
  "status": "task_status"
}

