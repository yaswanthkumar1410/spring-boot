# TaskFlow — Task Management REST API

A production-ready REST API for task and user management built with Spring Boot, PostgreSQL, and JWT authentication.

## Tech Stack

- **Java 24** / **Spring Boot 3.4**
- **PostgreSQL** — persistent data storage
- **Spring Data JPA** — ORM and repository layer
- **Spring Security + JWT** — stateless authentication
- **Maven** — build and dependency management
- **Bean Validation** — input validation with Jakarta constraints
- **Spring Actuator** — health monitoring

## Features

- Full CRUD operations for tasks and users
- JWT-based stateless authentication
- User-Task relationship (One-to-Many)
- Input validation with custom error responses
- Global exception handling
- Pagination and sorting
- Custom search queries (by title, completion status)
- Profile-based configuration (dev/prod)
- Health check endpoint

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Login and receive JWT token |

### Tasks
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | Get all tasks |
| POST | `/tasks` | Create a new task |
| GET | `/tasks/{id}` | Get task by ID |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |
| GET | `/tasks/search?completed=true` | Filter tasks by status |
| GET | `/tasks/search/title?keyword=spring` | Search tasks by title |
| GET | `/tasks/paged?page=0&size=5` | Paginated task list |

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Create a new user |
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |

### Monitoring
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/actuator/health` | Application health check |

## Project Structure

```
src/main/java/com/yash/notifier/
├── NotifierApp.java                 # Application entry point
├── config/
│   ├── SecurityConfig.java          # Spring Security + JWT config
│   ├── JwtUtil.java                 # JWT token generation/validation
│   └── JwtFilter.java              # JWT authentication filter
├── controller/
│   ├── AuthController.java          # Login endpoint
│   ├── TaskController.java          # Task endpoints
│   └── UserController.java          # User endpoints
├── dto/
│   ├── TaskRequest.java             # Task input DTO
│   ├── TaskResponse.java            # Task output DTO
│   ├── UserRequest.java             # User input DTO
│   └── UserResponse.java            # User output DTO
├── exception/
│   ├── GlobalExceptionHandler.java  # Centralized error handling
│   ├── TaskNotFoundException.java
│   └── ResourceNotFoundException.java
├── model/
│   ├── Task.java                    # Task entity
│   └── User.java                    # User entity
├── repository/
│   ├── TaskRepository.java          # Task data access
│   └── UserRepository.java          # User data access
└── service/
    ├── TaskService.java             # Task business logic
    └── UserService.java             # User business logic
```

## Getting Started

### Prerequisites
- Java 24+
- Maven 3.9+
- PostgreSQL 18+

### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/yaswanthkumar1410/spring-boot.git
   cd spring-boot
   ```

2. Create a PostgreSQL database
   ```sql
   CREATE DATABASE taskdb;
   ```

3. Create `src/main/resources/application-dev.properties`
   ```properties
   server.port=8080
   app.name=TaskFlow (DEV)
   spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

5. Login to get a JWT token
   ```bash
   curl -X POST localhost:8080/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username": "yash", "password": "123"}'
   ```

6. Use the token to access protected endpoints
   ```bash
   curl localhost:8080/tasks \
     -H "Authorization: Bearer <your_token>"
   ```
