# Banking Backend

A REST API built with Java, Spring Boot, Spring Data JPA, and PostgreSQL that simulates basic banking operations.

The application supports customer and account management, deposits, withdrawals, transfers between accounts, and transaction filtering. It also includes validation, error handling, and safeguards against overdrafts and transactions on inactive accounts.


## Technologies Used

- **Java** – Main programming language
- **Spring Boot** – Framework for building and running the REST API
- **Spring Web** – Handles HTTP requests and API endpoints
- **Spring Data JPA / Hibernate** – Connects Java objects to the database
- **PostgreSQL** – Stores customer, account, transaction, and transfer data
- **Docker Compose** – Runs the PostgreSQL database locally
- **Springdoc OpenAPI / Swagger UI** – Provides interactive API documentation
- **Maven** – Manages dependencies and builds the project


## Features

- **Customer Management** – Create, view, update, and delete customers.
- **Account Management** – Create checking and savings accounts, view balances, and update account statuses.
- **Transaction Processing** – Deposit and withdraw money while preventing overdrafts.
- **Funds Transfers** – Transfer money between accounts with automatic rollback if a transfer fails.
- **Transaction Filtering** – Search transactions by account number, type, date range, or amount.
- **Validation** – Prevent invalid amounts and transactions on suspended or closed accounts.
- **Error Handling** – Return consistent JSON error responses with appropriate HTTP status codes.


## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17
- Docker Desktop
- Git

### 1. Clone the Repository

```bash
git clone git@github.com:Thrivent-ETS-Cohort-2026/ashameem-banking-backend.git
cd ashameem-banking-backend
```

### 2. Start the PostgreSQL Database

Navigate to the `db` directory and start the database using Docker Compose:

```bash
cd db
docker compose up -d
cd ..
```

### 3. Run the Spring Boot Application

From the project root, run:

```bash
./mvnw spring-boot:run
```

The application will start locally on port `8080`.

### 4. Access Swagger UI

Once the application is running, open:

http://localhost:8080/swagger-ui/index.html

You can use Swagger UI to explore and test the API endpoints.


## Documentation

For more detailed information about the project, see:

- [API Documentation](docs/api-documentation.md) – API endpoints, request examples, transaction filtering, and error handling.
- [Entity Relationship Diagram](docs/banking-backend.png) – Database tables and their relationships.

Interactive API documentation is also available through Swagger UI when the application is running.
