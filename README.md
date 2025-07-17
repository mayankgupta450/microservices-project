:-> Microservices Project - User, Product, Order

Overview
A simple microservices system using Spring Boot & Docker with three services:
- User Service (H2 DB)
- Product Service (MongoDB)
- Order Service (MySQL + communicates with both above)

:->Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Docker (partially attempted)
- Swagger (API Docs)
- MySQL, MongoDB, H2 (in-memory)

:-> How to Run Each Service

:-> User Service
- Port: 8081
- DB: H2 (in-memory)
- Run: Run as java application

:-> Product Service
- Port: 8082
- DB: MongoDB (run via Docker)
- Run: Run as java application

:->Order Service
- Port: 8083
- DB: MySQL (set up locally)
- Depends on: `user-service` and `product-service`
- Run:  Run as java application

:-> Swagger url 
- User: `http://localhost:8081/swagger-ui/index.html`
- Product: `http://localhost:8082/swagger-ui/index.html`
- Order: `http://localhost:8083/swagger-ui/index.html`

:-> Docker
To run MongoDB via Docker:

docker run -d -p 27017:27017 --name mongo-container mongo
(Note: Dockerized services partially attempted)

## Testing
- Use Swagger to test all CRUD endpoints
- Test order placement: Order service verifies both user and product before placing order


Spring Boot Actuator Endpoints:->

http://localhost:8081/actuator/health → Order Service

http://localhost:8082/actuator/health → User Service

http://localhost:8083/actuator/health → Product Service

