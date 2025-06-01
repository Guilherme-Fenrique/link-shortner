# 🔗 Link Shortner

A simple URL shortener built with **Java 21 + Spring Boot** and backed by a **PostgreSQL** database. It provides a REST API to generate short URLs that redirect to original long URLs.


---

## 🚀 Features

- Accepts a `longUrl` via POST request and returns a shortened URL
- Random alphanumeric short codes (5 to 10 characters)
- Stores mappings in a PostgreSQL database
- Configuration via `application.properties`
- Dockerized PostgreSQL setup

---

## 📦 Tech Stack

- Java 21
- Spring Boot
- PostgreSQL
- Docker Compose

---

## 🛠️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Guilherme-Fenrique/link-shortner.git
cd link-shortner
```

### 2. Start the PostgreSQL database

```bash
docker-compose up -d
```

This will launch a PostgreSQL container using the `docker-compose.yml` configuration.

### 3. Configure the application

Your `application.properties` file (in `src/main/resources/`) is already configured with:

```properties
spring.application.name=LinkShortner

# Server config
server.port=8080
app.base-url=http://localhost:${server.port}/

# Datasource settings
spring.datasource.url=jdbc:postgresql://localhost:5432/linkshortner
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

> You can update any values as needed for your local setup.

### 4. Run the application

With an IDE (e.g. IntelliJ), run the main class: `LinkShortnerApplication.java`

Or via Maven:

```bash
./mvnw spring-boot:run
```

---

## 📬 API Usage

### POST `/api/shorten`

Receives a long URL in the request body and returns a shortened URL.

**Request:**

```http
POST http://localhost:8080/api/shorten
Content-Type: application/json
```

**Body:**

```json
{
  "longUrl": "http://google.com"
}
```

**Response:**

```json
{
  "id": 1,
  "longUrl": "http://google.com",
  "shortUrl": "http://localhost:8080/FWyQBx",
  "createdAt": "2025-06-01T23:47:41.5394667"
}
```

---

### GET `/{shortUrl}`

When someone accesses a short link like:

```http
GET http://localhost:8080/FWyQBx
```

The request is handled by a method annotated with:

```java
@GetMapping("/{shortUrl}")
```

This method looks up the original `longUrl` from the database and performs an HTTP 302 redirect to it.

---

## ⚙️ Configuration

The application is fully configured via `application.properties`.  
Key settings include:

- Database URL: `jdbc:postgresql://localhost:5432/linkshortner`
- Username: `postgres`
- Password: `admin`
- Base URL: `http://localhost:8080/`

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
