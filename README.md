# Kafka Producer and Consumer Service

## Overview

This project is a simple Kafka producer and consumer service using Spring Boot. The producer service exposes a REST API
to create events, which are sent to a Kafka topic. A Kafka listener component in the project receives messages from the
Kafka topic and processes them.

## Prerequisites

- Java 17
- Docker and Docker Compose
- Maven (Optional)
- Docker && Docker compose

## Key Components

- ***KafkaProducerService***: Handles sending messages to Kafka topics.
- ***KafkaController***: REST API endpoint for creating Kafka events.
- ***CustomKafkaListenerComponent***: Responsible for listening to Kafka topics and processing received messages.
- ***GlobalControllerAdvice***: Global exception handler for the application.
- ***WebException***: Custom exception class to handle web-specific errors.

## Configuration

### Application Configuration

The application's settings are available in the application.yaml file, located in the `src/main/resources` directory.
This includes configurations for the Spring Boot application and Kafka.

### Kafka Configuration

Docker Compose handles the Kafka and Zookeeper configurations. You can find these settings in the `docker-compose.yml`
file.

## How to Run

### Starting Kafka and Zookeeper

1. Navigate to the directory containing the docker-compose.yml file.
2. Run the following command to start the services:

````shell
docker-compose up -d
````

### Build and Run the Application

Using Maven:

You can use Maven to run the application directly:

```shell
mvn spring-boot:run
```

Using JAR:

Or, build a JAR file and then run it:

```shell
mvn clean package
java -jar target/kafka-producer-consumer-service-0.0.1.jar
```

### Swagger UI

After starting the application, you can access the Swagger UI at:

http://localhost:8081/swagger-ui/

### Kafka UI

Kafka UI will be accessible at:

http://localhost:8080/

### API Endpoints

Create Event:

POST http://localhost:8081/api/kafka/createEvent

### Exception Handling

The application utilizes a global exception-handling mechanism, implemented in the `GlobalControllerAdvice` class, to
manage exceptions uniformly across all controllers.


### Logging

Logging configurations are present in the `application.yaml` file, with the root logging level set to `INFO`.