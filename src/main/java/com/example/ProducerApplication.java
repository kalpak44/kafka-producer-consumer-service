package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProducerApplication serves as the entry point for the Spring Boot application.
 *
 * <p>This class is annotated with @SpringBootApplication, which signifies that it's a Spring Boot
 * application. The main method invokes SpringApplication.run() to bootstrap the application.
 */
@SpringBootApplication
public class ProducerApplication {

  /**
   * The main method serves as the entry point for the application.
   *
   * <p>This method delegates to Spring Boot's SpringApplication class, which bootstraps the
   * application.
   *
   * @param args Command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }
}
