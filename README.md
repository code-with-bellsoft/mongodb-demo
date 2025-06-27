# MongoDB Demo App

A Spring Boot application demonstrating the use of Spring Data MongoDB

- Integration with Spring Boot
- DB migrations with Mongock
- CRUD operations
- Derived query methods
- MongoTemplate for more complex queries
- Aggregation Pipelines
- Projections


## Prerequisites

- Java 24
- Docker and Docker Compose

## Running the Application

### Running Locally

1. Start the MongoDB container:
   ```bash
   docker-compose up -d mongodb
   ```

2. Build and run the app:
   ```bash
   ./mvnw spring-boot:run
   ```