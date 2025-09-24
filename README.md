📚 Customer & Product Management API (Reactive)

This project is a Spring Boot 3.3.x Reactive REST API for managing Customers and Products.
It demonstrates the use of Spring WebFlux, PostgreSQL R2DBC integration, Swagger/OpenAPI for documentation, reactive error handling, and logging with Logback.

🚀 Features

✅ CRUD APIs for Customers and Products<br>
✅ Reactive & Non-Blocking (Spring WebFlux + Project Reactor)<br>
✅ RESTful JSON standard (GET, POST, PUT, DELETE)<br>
✅ PostgreSQL R2DBC database integration<br>
✅ Swagger UI for API documentation & testing<br>
✅ Centralized error handling & validation (reactive)<br>
✅ Logging with Logback<br>
✅ Unit Tests with JUnit 5 & WebTestClient

🛠️ Tech Stack

Java 17 (OpenJDK)

Spring Boot 3.3.x (LTS)

Spring WebFlux

Spring Data R2DBC

PostgreSQL

Springdoc OpenAPI (Swagger UI)

Logback for logging

JUnit 5 + WebTestClient for testing

Maven as build tool

▶️ How to Run
1. Clone the repo:<br>
   git clone https://github.com/your-username/java-assessment-.git<br>
   cd java-assessment-

2. Configure PostgreSQL in application.properties (or application.yml):<br>
   spring.r2dbc.url=r2dbc:postgresql://localhost:5432/customerdb<br>
   spring.r2dbc.username=yourusername<br>
   spring.r2dbc.password=yourpassword<br>

3. Build & run:
   mvn clean install
   mvn spring-boot:run

4. Access API:

Swagger UI → http://localhost:8080/swagger-ui.html

📝 Logging

All API requests and responses are logged with Logback

Logs are stored in: logs/app.log

✅ Testing

Run unit tests with:

mvn test


Uses JUnit 5 and Spring WebFlux WebTestClient for reactive endpoint testing.