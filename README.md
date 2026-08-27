# 📑 LosLibros - Borrowing Service

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue.svg)](https://www.mysql.com/)
[![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-red.svg)](https://mapstruct.org/)

The **Borrowing Service** orchestrates the book lending lifecycle, checkout periods, return dates, and circulation transaction records in the LosLibros Library Management System.

---

## 🌟 Features

- **Lending & Return Transactions**: Records new book borrowings, tracks due dates, and marks returned items.
- **Relational Persistence**: Uses **MySQL** and Spring Data JPA / Hibernate for ACID-compliant transaction records.
- **Dynamic Status Tracking**: Determines book circulation state (`BORROWED` vs `RETURNED`).
- **Validation**: Ensures mandatory presence of `borrowDate`, `dueDate`, `bookIsbn`, and `memberId`.
- **Cloud Integration**: Connects with **Spring Cloud Config** and **Eureka Service Registry**.

---

## ⚙️ Configuration & Environment

- **Service Name**: `borrowing-service`
- **Port**: Dynamic (`0` - registered to Eureka) or configured in Config Server.
- **Database**: MySQL
  - Default URL: `jdbc:mysql://localhost:3306/db-loslibros?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true`
  - Default Username: `root`
  - Default Password: `mysql`

---

## 📡 API Endpoints (`/api/v1/borrowings`)

| Method | Endpoint | Request Body | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/borrowings` | `application/json` | Creates a new borrowing record |
| `PUT` | `/api/v1/borrowings/{borrowingId}` | `application/json` | Updates a borrowing record (e.g. records `returnDate`) |
| `GET` | `/api/v1/borrowings/{borrowingId}` | None | Retrieves a borrowing record by ID |
| `GET` | `/api/v1/borrowings` | None | Retrieves all borrowing records |
| `DELETE` | `/api/v1/borrowings/{borrowingId}` | None | Deletes a borrowing record |

---

## 📝 Request & Response Models

### 1. Create Borrowing Request (`POST /api/v1/borrowings`)
```json
{
  "borrowDate": "2025-03-01",
  "dueDate": "2025-03-15",
  "bookIsbn": "9781234567897",
  "memberId": "M001"
}
```

### 2. Update Borrowing (Return Book) Request (`PUT /api/v1/borrowings/{borrowingId}`)
```json
{
  "borrowDate": "2025-03-01",
  "dueDate": "2025-03-15",
  "returnDate": "2025-03-10",
  "bookIsbn": "9781234567897",
  "memberId": "M001"
}
```

### 3. Sample Borrowing Response (`JSON`)
```json
{
  "borrowingId": 1,
  "borrowDate": "2025-03-01",
  "dueDate": "2025-03-15",
  "returnDate": "2025-03-10",
  "bookIsbn": "9781234567897",
  "memberId": "M001",
  "status": "RETURNED"
}
```

---

## 🚀 Running the Service

### Prerequisites
1. **Config Server** (Port `9000`) and **Service Registry** (Port `9001`) must be running.
2. **MySQL Server** running with database `db-loslibros`.

### Launch via Maven Wrapper

```bash
cd services/borrowing-service
./mvnw spring-boot:run
```

### Launch via Built JAR

```bash
./mvnw clean package -DskipTests
java -jar target/Borrowing-Service-1.0.0.jar
```
