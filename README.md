# Spring Boot Task Management API

## Features

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

```json
Response:
{
  "message": "User registered successfully."
}
