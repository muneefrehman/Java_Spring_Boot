# 📚 Demo REST API: Books & Authors

<div>
  <img src="https://img.shields.io/badge/-Java-007396?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/-PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/-Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
</div>

This is a **Java Spring Boot** demo REST API project that provides **CRUD functionality for Authors and Books**. This project is **backend-only** and demonstrates API design, database integration, and automated testing using CI workflows. The database used is **PostgreSQL**, with an **H2 in-memory database** for tests.

---

## ✨ Features

- CRUD APIs for **Authors**:
  - Create, Read, Update, Delete
- CRUD APIs for **Books**:
  - Create, Read, Update, Delete
  - Pagination support
- PostgreSQL as persistent database
- H2 in-memory database for testing
- Automated tests using **JUnit 5**
- CI workflow with **GitHub Actions**:
  - Run tests on push
  - Generate test reports

---

## 🛠 Tech Stack

- Java 17 ☕  
- Spring Boot 4.0.1 🌱  
- Maven 4.0.1 🧰  
- PostgreSQL 18+ 🐘  
- H2 Database (for testing) 🧪  
- Docker & Docker Compose 🐳  
- GitHub Actions (CI) ⚡

---

## 🏗 Project Structure

```text
demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── config/          # Configuration classes (Security, Beans, etc.)
│   │   │   ├── controllers/     # REST API Endpoints
│   │   │   ├── domain/          # Entities and Data Models
│   │   │   ├── mappers/         # Data Transfer Object (DTO) mappers
│   │   │   ├── repositories/    # Data access layer (Spring Data JPA)
│   │   │   ├── services/        # Business logic layer
│   │   │   └── DemoApplication  # Main entry point
│   │   └── resources/           # Application properties and static assets
│   └── test/                    # Unit and Integration tests
├── Dockerfile                   # Containerization configuration
├── docker-compose.yml           # Multi-container orchestration (DB, App, etc.)
└── pom.xml                      # Maven dependencies and project config
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/muneefrehman/Java_Spring_Boot.git
cd demo
```

### 2. Configure PostgreSQL:

Ensure PostgreSQL is installed and running on your machine. Create a database:

```sql
CREATE DATABASE demo_db;
CREATE USER demo_user WITH ENCRYPTED PASSWORD 'changeme';
GRANT ALL PRIVILEGES ON DATABASE demo_db TO demo_user;
```

### 3. Update application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demo_db
spring.datasource.username=demo_user
spring.datasource.password=changeme
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 4. Build & Run the project:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

The application should now be running at http://localhost:8080 🚀

---

## 🐳 Docker Setup

You can also run the project using Docker for easier setup:

### 1. Build Docker image:

```bash
docker build -t demo-api .
```

### 2. Use Docker Compose:

Your docker-compose.yml should look like this:

```yaml
version: '3.1'

services:
  app:
    image: demo-api
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/demo_db
      SPRING_DATASOURCE_USERNAME: demo_user
      SPRING_DATASOURCE_PASSWORD: changeme

  db:
    image: postgres:18
    environment:
      POSTGRES_USER: demo_user
      POSTGRES_PASSWORD: changeme
      POSTGRES_DB: demo_db
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql

volumes:
  postgres_data:
```

### 3. Start the containers:

```bash
docker-compose up -d
```

The API will be available at http://localhost:8080 🌐

---

## 📝 API Endpoints

### Authors

| Method | Endpoint | Description |
| :--- | :--- | --- |
| GET | /authors | List all authors |
| GET | /authors/{id} | Get author by ID |
| POST | /authors | Create a new author |
| PUT | /authors/{id} | Update an author |
| DELETE | /authors/{id} | Delete an author |

### Books

| Method | Endpoint | Description |
| :--- | :--- | --- |
| GET | /books | List all books (supports pagination) |
| GET | /books/{isbn} | Get book by ISBN |
| PUT | /books/{isbn} | Create a new book |
| PUT | /books/{isbn} | Update a book |
| DELETE | /books/{isbn} | Delete a book |

---

## 🧪 Testing

### Run tests manually

```bash
./mvnw clean verify
```

### CI Testing

- The project has GitHub Actions workflows that run tests automatically on push 🔄
- Test results and coverage are published in the workflow logs 📊
- H2 in-memory database is used for testing, no need for PostgreSQL 🧪

---

## ⚡ CI/CD Workflow

 - GitHub Actions is configured to run:
   - ./mvnw clean verify
   - Generate test reports 📄

---

## 📌 Notes

- This project is backend-only; there is no frontend ❌
- Database connection should use service name when using Docker Compose
- Pagination is supported on /books endpoint
- Use environment variables for sensitive information when deploying to production 🔒
