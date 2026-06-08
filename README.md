# Student Management System

## Overview

Student Management System is a secure RESTful web application developed using Spring Boot and PostgreSQL as part of the PlatformCommons Assignment.

The application enables administrators to manage students, courses, and course assignments, while students can manage their own profiles and enrolled courses.

The project follows enterprise-level backend development practices including:

* Layered Architecture
* JWT Authentication
* Role-Based Authorization (RBAC)
* DTO Pattern
* Standardized API Responses
* Swagger/OpenAPI Documentation
* Spring Data JPA & Hibernate
* PostgreSQL Database

---

# Technology Stack

| Technology      | Version |
| --------------- | ------- |
| Java            | 21      |
| Spring Boot     | 3.4.0   |
| Spring Security | 6.x     |
| Spring Data JPA | 3.x     |
| Hibernate       | 6.x     |
| PostgreSQL      | 16+     |
| JWT             | 0.11.5  |
| Swagger/OpenAPI | 2.8.8   |
| Maven           | 3.9+    |
| Lombok          | Latest  |

---

# Architecture

```text
Controller Layer
        |
        v
Service Layer
        |
        v
Repository Layer
        |
        v
PostgreSQL Database
```

Project Structure:

```text
src/main/java
|
├── controller
│   ├── AuthController
│   ├── AdminController
│   └── StudentController
│
├── service
├── service/impl
│
├── repository
│
├── entity
│
├── model
│
├── security
│
├── exception
│
└── config
```

---

# Security

The application uses:

* Spring Security
* JWT Authentication
* Role-Based Access Control

Supported Roles:

```text
ROLE_ADMIN
ROLE_STUDENT
```

All protected APIs require:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# Authentication APIs

## Admin Login

```http
POST /api/auth/admin/login
```

### Request

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### Response

```json
{
  "token": "jwt-token"
}
```

---

## Student Login

```http
POST /api/auth/student/login
```

### Request

```json
{
  "studentCode": "STU-12345678",
  "dateOfBirth": "1998-05-15"
}
```

### Response

```json
{
  "token": "jwt-token"
}
```

---

# Admin Operations

Base URL:

```text
/api/admin
```

Role Required:

```text
ROLE_ADMIN
```

---

## 1. Create Student

```http
POST /api/admin/create-student
```

Creates a new student admission record.

---

## 2. Create Course

```http
POST /api/admin/create-course
```

Creates a new course.

### Sample Request

```json
{
  "courseName": "Java Backend Development",
  "description": "Spring Boot and Microservices",
  "courseType": "TECHNICAL",
  "durationInMonths": 6,
  "topics": [
    "Java",
    "Spring Boot",
    "Kafka",
    "PostgreSQL"
  ]
}
```

---

## 3. Assign Course To Student

```http
POST /api/admin/assign-course
```

### Request

```json
{
  "studentCode": "STU-12345678",
  "courseName": "Java Backend Development"
}
```

---

## 4. Search Students By Name

```http
GET /api/admin/students/search?studentName=Anish
```

Returns matching students.

---

## 5. Get Students Assigned To Course

```http
GET /api/admin/courses/{courseName}/students
```

Returns all students enrolled in a course.

---

# Student Operations

Base URL:

```text
/api/students
```

Role Required:

```text
ROLE_STUDENT
```

---

## 1. Get Student Profile

```http
GET /api/students/{studentCode}
```

Returns student profile details.

---

## 2. Update Student Profile

```http
PATCH /api/students/update-profile
```

Allows student to update:

* Email
* Mobile Number
* Parent Name

---

## 3. Leave Course

```http
DELETE /api/students/leave-course
```

### Request

```json
{
  "studentCode": "STU-12345678",
  "courseName": "Java Backend Development"
}
```

Removes the course from student's enrolled courses.

---

# Standard API Response Format

All business APIs return a standardized response structure.

```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": {},
  "timestamp": "2026-06-08T10:30:00"
}
```

---

# Database Tables

## students

Stores:

* Student Code
* Name
* Date Of Birth
* Email
* Mobile Number
* Gender
* Parent Name

---

## student_addresses

Stores:

* Permanent Address
* Current Address

---

## courses

Stores:

* Course Information
* Duration
* Topics

---

## student_course

Many-to-Many mapping table between:

* Students
* Courses

---

## course_topics

Stores course topics.

---

## app_users

Stores administrator login information.

Columns:

```text
id
username
password
role
```

---

# Swagger Documentation

Swagger UI:

```text
http://localhost:8083/swagger-ui/index.html
```

OpenAPI Specification:

```text
http://localhost:8083/api-docs
```

---

# Application Configuration

Example:

```yaml
server:
  port: 8083

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bookmyshow
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update

    show-sql: true

jwt:
  secret: 1234567890123456789012345678901234567890
  expiration: 86400000
```

---

# Running The Application

## 1. Clone Repository

```bash
git clone <repository-url>
```

---

## 2. Create PostgreSQL Database

```sql
CREATE DATABASE bookmyshow;
```

---

## 3. Update Database Credentials

Configure:

```yaml
application.yml
```

with your PostgreSQL username and password.

---

## 4. Build Project

```bash
mvn clean install
```

---

## 5. Run Application

```bash
mvn spring-boot:run
```

Application URL:

```text
http://localhost:8083
```

---

# Sample Admin User

Insert an administrator record:

```sql
INSERT INTO app_users
(
    username,
    password,
    role
)
VALUES
(
    'admin',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoOHiW9Pz9nP9V5nYeD1yfknhkXaoCA8Dm',
    'ROLE_ADMIN'
);
```

Credentials:

```text
Username : admin
Password : admin123
```

---

# Key Features Implemented

✔ JWT Authentication

✔ Role-Based Authorization

✔ Student Admission Management

✔ Course Management

✔ Course Assignment Management

✔ Student Self-Service Operations

✔ Swagger Documentation

✔ Standardized API Responses

✔ PostgreSQL Integration

✔ Spring Security

✔ DTO-Based Architecture

✔ JPA/Hibernate ORM

---

# Future Enhancements

* Refresh Token Support
* Pagination & Sorting
* Docker Containerization
* Kubernetes Deployment
* Audit Logging
* Email Notifications
* Unit Testing & Integration Testing
* CI/CD Pipeline

---

# Author

Anish Kumar

Java Backend Developer

Spring Boot | Microservices | PostgreSQL | Kafka
