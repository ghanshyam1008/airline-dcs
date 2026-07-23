# ✈️ Airline Departure Control System (Airline DCS)

A production-style Airline Departure Control System (DCS) built using **Java, Spring Boot, Spring Security, Hibernate, PostgreSQL, and REST APIs**. The application simulates real-world airline operations by managing airports, airlines, aircraft, flights, passengers, check-in, boarding, and departure processes while following enterprise software development practices.

---

# 📌 Project Overview

The Airline Departure Control System is a backend application designed to automate airline operations from flight creation to passenger boarding. It provides secure REST APIs with role-based access control and implements real-world business rules using a scalable layered architecture.

This project was built to gain hands-on experience in enterprise Java development, Spring Boot, database design, security, testing, and clean coding principles.

---

# 🚀 Tech Stack

## Backend
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate ORM
- Spring Security
- JWT Authentication & Authorization
- Maven

## Database
- PostgreSQL

## API Testing
- Postman

## Version Control
- Git
- GitHub

## Logging
- SLF4J
- Logback

## Testing
- JUnit 5
- Mockito
- MockMvc

---

# 🏗️ Architecture

The project follows a production-ready layered architecture.

```
Controller
      ↓
Service Interface
      ↓
Service Implementation
      ↓
Repository
      ↓
PostgreSQL Database
```

Additional Design Patterns

- Repository Pattern
- DTO Pattern
- Dependency Injection
- Layered Architecture

---

# 📂 Project Structure

```
src
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
│      └── impl
├── config
├── util
└── AirlineDcsApplication
```

---

# ✨ Features

## Airport Management
- Airport CRUD Operations
- Airport Validation
- Airport Status Management

## Airline Management
- Airline CRUD Operations
- Airport-Airline Mapping
- Business Rule Validation

## Aircraft Management
- Aircraft CRUD Operations
- Airline-Aircraft Relationship
- Aircraft Capacity Management
- Aircraft Availability Management

## Flight Management
- Flight CRUD Operations
- Flight Scheduling
- Origin & Destination Airport Mapping
- Airline Assignment
- Aircraft Assignment
- Flight Status Management
- Business Rule Validation

## Passenger Management
- Passenger Registration
- Passenger Search
- Passenger Profile Management

## Booking Management
- Flight Booking
- PNR Generation
- Seat Availability
- Booking History

## Check-In Module
- Passenger Check-In
- Seat Assignment
- Boarding Pass Generation
- Baggage Information

## Boarding Module
- Boarding Validation
- Passenger Boarding
- Final Passenger List

## Flight Control Module
- Flight Delay
- Flight Cancellation
- Gate Assignment
- Aircraft Change
- Flight Closure

## Reports
- Flight Reports
- Passenger Reports
- Load Factor Reports
- Operational Reports

## Security
- User Authentication
- JWT Token Generation
- JWT Validation
- Role-Based Authorization
- Password Encryption
- Protected REST APIs

---

# 🔐 User Roles

- Admin
- Supervisor
- Check-In Agent
- Boarding Agent
- Flight Controller

Each role has different permissions enforced using Spring Security.

---

# 🗄️ Database Design

```
Airport
   │
   ├──────────────┐
   ▼              ▼
Airline        Flight
   │              ▲
   ▼              │
Aircraft──────────┘
   │
   ▼
Passenger
   │
   ▼
Booking
   │
   ▼
Check-In
   │
   ▼
Boarding
```

Implemented Relationships

- One-to-One
- One-to-Many
- Many-to-One
- Many-to-Many

---

# 🛠️ REST APIs

### Airport
- Create Airport
- Update Airport
- Delete Airport
- Get Airport
- Get All Airports

### Airline
- Create Airline
- Update Airline
- Delete Airline
- Get Airline
- Get Airlines by Airport

### Aircraft
- Create Aircraft
- Update Aircraft
- Delete Aircraft
- Get Aircraft
- Get Aircraft by Airline

### Flight
- Create Flight
- Update Flight
- Delete Flight
- Search Flights
- Flight Schedule

### Passenger
- Passenger Registration
- Passenger Search

### Booking
- Book Flight
- Cancel Booking
- Booking History

### Check-In
- Check-In Passenger
- Seat Assignment

### Boarding
- Board Passenger
- Boarding Status

---

# ✅ Enterprise Features

- Layered Architecture
- RESTful APIs
- DTO Pattern
- Bean Validation
- Global Exception Handling
- Centralized Logging
- Constructor-based Dependency Injection
- Spring Data JPA
- Hibernate ORM
- Entity Relationships
- Custom Repository Methods
- JWT Authentication
- Role-Based Access Control
- Transaction Management
- Pagination & Sorting
- Custom Exception Handling
- API Testing using Postman
- Git Version Control

---

# 📚 Concepts Implemented

### Java
- OOP
- Exception Handling
- Collections
- Generics
- Streams
- Optional
- Lambda Expressions

### Spring Boot
- Dependency Injection
- IoC
- Spring MVC
- REST APIs
- Validation
- ResponseEntity
- Logging
- Configuration

### Spring Data JPA
- Entity Mapping
- Relationships
- JPQL
- Derived Queries
- Transactions
- Lazy & Eager Fetching

### Spring Security
- Authentication
- Authorization
- JWT
- Password Encoding
- Role-Based Access Control

### Database
- ER Design
- Normalization
- Foreign Keys
- Constraints
- Indexes

### Testing
- JUnit
- Mockito
- MockMvc

---

# 🚀 Future Enhancements

- Flight Notifications
- Email Service
- SMS Alerts
- Redis Caching
- Docker Deployment
- CI/CD Pipeline
- Microservices Migration
- API Gateway
- Service Discovery

---

# 👨‍💻 Author

**Ghanshyam Verma**

Java Backend Developer

### Skills

- Java
- Spring Boot
- Spring MVC
- Spring Security
- JWT Authentication
- Hibernate
- Spring Data JPA
- REST APIs
- PostgreSQL
- Maven
- Git
- GitHub
- JUnit
- Mockito
- Postman
- SLF4J Logging
