# PlatformCommons Assignment

## Overview

PlatformCommons Assignment is a Spring Boot REST API application developed to demonstrate enterprise-level backend development practices including:

* JWT Authentication & Authorization
* Role-Based Access Control (RBAC)
* Student Management
* Course Management
* RESTful API Design
* DTO-Based Request/Response Handling
* Global Exception Handling
* Spring Security Integration
* PostgreSQL Database Integration
* JPA/Hibernate Relationships

The application follows a layered architecture and industry-standard coding practices suitable for production-grade applications.

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3.x
* Spring Security
* Spring Data JPA
* Hibernate
* JWT (JSON Web Token)

### Database

* PostgreSQL

### Build Tool

* Maven

### Additional Libraries

* Lombok
* Jakarta Validation
* ModelMapper (if used)
* Jackson

---

## Project Structure

```text
src/main/java/com/platformcommons

├── config
│   └── SecurityConfig
│
├── controller
│   ├── AuthController
│   ├── StudentController
│   └── CourseController
│
├── dto
│   ├── request
│   └── response
│
├── entity
│   ├── User
│   ├── Student
│   ├── Course
│   └── Role
│
├── repository
│   ├── UserRepository
│   ├── StudentRepository
│   └── CourseRepository
│
├── service
│   ├── AuthService
│   ├── StudentService
│   └── CourseService
│
├── security
│   ├── JwtAuthenticationFilter
│   ├── JwtService
│   └── CustomUserDetailsService
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── ResourceNotFoundException
│   └── UnauthorizedException
│
└── PlatformCommonsApplication
```

---

## Features

### Authentication

* User Registration
* User Login
* JWT Token Generation
* Stateless Authentication

### Authorization

Supported Roles:

* ADMIN
* STUDENT

Role-Based Endpoint Protection using:

```java
@PreAuthorize(...)
```

Examples:

```java
@PreAuthorize("hasRole('ADMIN')")
```

```java
@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
```

---

## Student Management

### Capabilities

* Create Student
* Get Student by ID
* Get All Students
* Update Student
* Delete Student

### Sample Student Object

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 22
}
```

---

## Course Management

### Capabilities

* Create Course
* Get Course Details
* Update Course
* Delete Course
* Assign Students to Courses

### Sample Course Object

```json
{
  "courseName": "Java Backend Development",
  "courseCode": "JAVA101",
  "description": "Spring Boot and Microservices"
}
```

---

## Entity Relationships

### Student ↔ Course

Many-to-Many Relationship

```java
@ManyToMany
@JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private List<Course> courses;
```

```java
@ManyToMany(mappedBy = "courses")
private List<Student> students;
```

---

## API Endpoints

### Authentication APIs

| Method | Endpoint           | Description   |
| ------ | ------------------ | ------------- |
| POST   | /api/auth/register | Register User |
| POST   | /api/auth/login    | Login User    |

---

### Student APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/students      |
| GET    | /api/students      |
| GET    | /api/students/{id} |
| PUT    | /api/students/{id} |
| DELETE | /api/students/{id} |

---

### Course APIs

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | /api/courses      |
| GET    | /api/courses      |
| GET    | /api/courses/{id} |
| PUT    | /api/courses/{id} |
| DELETE | /api/courses/{id} |

---

## Security Flow

1. User registers.
2. User logs in.
3. JWT token is generated.
4. Client sends token in Authorization header.

```http
Authorization: Bearer <jwt-token>
```

5. JwtAuthenticationFilter validates token.
6. Spring Security authorizes request.
7. Requested API executes.

---

## Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

Handled Exceptions:

* ResourceNotFoundException
* BadRequestException
* AccessDeniedException
* MethodArgumentNotValidException
* Generic Exception

Standard Error Response:

```json
{
  "timestamp": "2026-06-07T10:00:00",
  "status": 404,
  "message": "Student not found"
}
```

---

## Database Configuration

Configure PostgreSQL in:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platformcommons
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Application

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Project

```bash
cd PlatformCommons
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts at:

```text
http://localhost:8080
```

---

## Future Enhancements

* Swagger/OpenAPI Documentation
* Docker Support
* Redis Caching
* Kafka Integration
* Audit Logging
* Pagination & Sorting
* File Upload Support
* Unit & Integration Tests
* CI/CD Pipeline

---

## Author

Anish Kumar

Java Backend Developer

### Key Skills Demonstrated

* Spring Boot
* Spring Security
* JWT Authentication
* REST APIs
* PostgreSQL
* JPA/Hibernate
* Exception Handling
* DTO Pattern
* Layered Architecture
* Role-Based Authorization
* Clean Code Principles
