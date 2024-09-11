# animal-shelter
# Animal Shelter Management System

## Overview

This project is a Spring Boot-based Animal Shelter Management System. It provides a robust backend for managing pets, users, donations, and authentication in an animal shelter environment.

## Features

- **User Management**: Register, login, and manage user accounts
- **Pet Management**: Add, update, and track pets available for adoption
- **Donation System**: Process and manage donations
- **Role-based Access Control**: Differentiate between admin and regular user access
- **JWT Authentication**: Secure API endpoints with JSON Web Tokens

## Technology Stack

- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- BCrypt Password Encoder
- JSON Web Tokens (JWT)
- Mockito (for unit testing)

## Project Structure

The project follows a standard Spring Boot application structure:

- `config`: Configuration classes for Spring Security and application settings
- `controllers`: REST API endpoints
- `dtos`: Data Transfer Objects for request/response handling
- `jwt`: JWT authentication filter and utilities
- `models`: Entity classes representing database tables
- `repositories`: JPA repositories for database operations
- `services`: Business logic implementation
- `test`: Unit tests for controllers and services

## Key Components

### Configuration

- `ApplicationConfig`: Configures beans for authentication, password encoding, and user details service
- `WebSecurityConfig`: Sets up security filters and access rules for different endpoints

### Controllers

- `AuthController`: Handles user authentication and registration
- `DonationController`: Manages donation-related operations
- `PetController`: Handles pet management operations
- `UserController`: Manages user-related operations

### Models

- `User`: Represents user entities and implements UserDetails for Spring Security
- `Pet`: Represents pet entities
- `Donation`: Represents donation entities

## API Endpoints

- Authentication: `/api/auth/login`, `/api/auth/register`
- Pets: `/api/v1/pets`
- Donations: `/api/v1/donations`
- Users: `/api/v1/users`

## Unit Testing

The project includes comprehensive unit tests for controllers and services using Mockito:

### Controller Tests

- Test cases cover all API endpoints
- Mock service layer to isolate controller logic
- Verify correct HTTP status codes and response bodies

### Service Tests

- Test business logic in isolation
- Mock repository layer to focus on service functionality
- Cover various scenarios including success cases and error handling

### Test Coverage

- DonationController and DonationService
- PetController and PetService
- UserController and UserService

To run the tests:
- ./mvnw test


## Setup and Installation

1. Clone the repository
2. Configure your database settings in `application.properties`
3. Run the application using Maven: `./mvnw spring-boot:run`

## Security

This application uses Spring Security with JWT for authentication. Include the JWT token in the Authorization header for protected endpoints.
