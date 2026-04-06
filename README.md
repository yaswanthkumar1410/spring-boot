# TaskFlow

Task management REST API with JWT authentication.

Built with Java 24, Spring Boot 3.4, PostgreSQL, and Spring Security.

## How to Run

1. Create a PostgreSQL database called `taskdb`
2. Create `src/main/resources/application-dev.properties`:
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. `mvn spring-boot:run`

## Endpoints

**Auth:** `POST /auth/login`

**Tasks:** `GET /tasks` · `POST /tasks` · `GET /tasks/{id}` · `PUT /tasks/{id}` · `DELETE /tasks/{id}`

**Search:** `GET /tasks/search?completed=true` · `GET /tasks/search/title?keyword=spring` · `GET /tasks/paged?page=0&size=5`

**Users:** `GET /users` · `POST /users` · `GET /users/{id}`

All endpoints except `/auth/login` require a Bearer token.
