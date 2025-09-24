📚 Customer & Product Management API

This project is a Spring Boot 3.3.x REST API for managing Customers and Products.
It demonstrates proper use of Spring MVC, PostgreSQL integration, Swagger for API documentation, logging with Logback, and basic error handling.

🚀 Features

✅ CRUD APIs for Customers and Products

✅ RESTful JSON standard (GET, POST, PUT, DELETE)

✅ PostgreSQL database integration

✅ Swagger UI for API documentation & testing

✅ Centralized error handling & validation

✅ Logging with Logback

✅ Unit Tests (JUnit + Spring Boot Test)


🛠️ Tech Stack

Java 17 (OpenJDK)

Spring Boot 3.3.x (LTS)

Spring Data JPA (Hibernate)

PostgreSQL

Springdoc OpenAPI (Swagger UI)

Logback for logging

JUnit 5 for testing

Maven as build tool


▶️ How to Run

Clone the repo:

git clone https://github.com/your-username/customer-product-api.git
cd customer-product-api


Configure PostgreSQL in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/customerdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Build & run:

mvn clean install<br>
mvn spring-boot:run


Access API:

Swagger UI → http://localhost:8080/swagger-ui/index.html

API Docs (JSON) → http://localhost:8080/v3/api-docs


📝 Logging

All API requests and responses are logged with Logback.

Logs can be found in logs/app.log.

✅ Testing

Run unit tests:

mvn test


