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

This application uses Mongock for manual definition of schema and indices. This is why the Indexed annotations are commented out. 
If you don't want to use Mongock, remove it from the code and uncomment @Indexed annotations in entity classes.

### Running Locally

1. Start the MongoDB container:
   ```bash
   docker-compose up -d
   ```

2. Build and run the app:
   ```bash
   ./mvnw spring-boot:run
   ```