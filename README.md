
# 🛒 Online Shopping Microservices Project

## Overview

This project is a microservices-based online shopping application developed using Java and Spring Boot. It leverages Spring Cloud components and Apache Kafka to provide a scalable, event-driven architecture suitable for modern e-commerce platforms.

## 🧰 Technologies Used

- **Java 11**
- **Spring Boot**
- **Spring Cloud** (Eureka, Config Server, Gateway)
- **Netflix Eureka** (Service Discovery)
- **Spring Cloud Config Server** (Centralized Configuration)
- **Spring Cloud Gateway** (API Gateway)
- **Apache Kafka** (Event Streaming)
- **MySQL** (Relational Database)
- **Maven** (Build Tool)
- **RESTful APIs**

## 🗂️ Project Structure

The repository is organized into the following modules:

```
online-shopping/
├── ApiGateway/                   # Handles routing and filtering of requests
├── customer-service/             # Manages customer-related operations
├── item_service/                 # Manages product/item-related operations
├── sales_order_service/          # Handles order processing and management
├── netflix-eureka-naming-server/ # Service registry for microservices
├── spring-cloud-config-server/   # Centralized configuration management
└── Security-realm.json           # Security configurations (e.g., Keycloak realm)
```

## 🚀 Getting Started

### Prerequisites

- **Java 11** installed
- **Maven** installed
- **Git** installed
- **Apache Kafka** installed and running
- **MySQL** installed and running

### Clone the Repository

```bash
git clone https://github.com/lokeshlavu01/online-shopping.git
cd online-shopping
```

### Build and Run Services

1. **Start Kafka** (Ensure Zookeeper and Kafka broker are running)

2. **Start the Config Server**

   ```bash
   cd spring-cloud-config-server
   mvn clean install
   mvn spring-boot:run
   ```

3. **Start the Eureka Naming Server**

   ```bash
   cd ../netflix-eureka-naming-server
   mvn clean install
   mvn spring-boot:run
   ```

4. **Start the API Gateway**

   ```bash
   cd ../ApiGateway
   mvn clean install
   mvn spring-boot:run
   ```

5. **Start the Customer Service**

   ```bash
   cd ../customer-service
   mvn clean install
   mvn spring-boot:run
   ```

6. **Start the Item Service**

   ```bash
   cd ../item_service
   mvn clean install
   mvn spring-boot:run
   ```

7. **Start the Sales Order Service**

   ```bash
   cd ../sales_order_service
   mvn clean install
   mvn spring-boot:run
   ```

Ensure each service starts successfully before proceeding to the next.

## 🧵 Event-Driven Architecture with Apache Kafka

This project uses **Apache Kafka** for asynchronous, event-driven communication between services.

### Kafka Integration Highlights

- **Producers**:
  - `sales_order_service` produces events such as `OrderCreated`.
- **Consumers**:
  - `item_service` and other services consume events for inventory and state updates.
- **Topics**:
  - Dedicated Kafka topics are used to transmit events between services, ensuring decoupled and reliable communication.

### Benefits

- Enables **real-time communication** between microservices.
- Facilitates **event sourcing** and auditing.
- Improves **scalability** and fault tolerance.

## 🗄️ Database

This project uses **MySQL** as the primary relational database for storing customer data, order details, and item information.

### Configuration

Each microservice is configured with its own datasource and schema. Make sure you have MySQL installed and create the required databases before running the services.

- Update the `application.properties` or `application.yml` files in each service with your MySQL credentials.
- Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### Note

Ensure the MySQL server is running and accessible before starting the microservices. Each service may have its own schema and table structure.

## 🔐 Security Configuration

The `Security-realm.json` file contains security configurations for integration with Keycloak.

1. **Install and Configure Keycloak**
2. **Import `Security-realm.json`**
3. **Update microservice configurations** with realm details

## 📬 API Endpoints

Once all services are running, APIs are accessible through the API Gateway:

- **Customer Service**: `http://localhost:<gateway-port>/customers`
- **Item Service**: `http://localhost:<gateway-port>/items`
- **Sales Order Service**: `http://localhost:<gateway-port>/orders`

Replace `<gateway-port>` with the actual port configured.

## 🧪 Testing the Application

Use **Postman**, **curl**, or any API client to test the endpoints. Include valid tokens if authentication is enabled.

## 📌 Notes

- Ensure all services are registered with Eureka.
- Kafka must be running before starting services that publish/subscribe.
- Config Server must be correctly linked to its repository.

## 🤝 Contributing

Contributions are welcome. Please fork and submit a pull request.
