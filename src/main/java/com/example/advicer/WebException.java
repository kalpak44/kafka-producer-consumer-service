package com.example.advicer;

import org.springframework.http.HttpStatus;

/**
 * WebException is a custom exception class that extends RuntimeException. It is used for handling
 * web-related exceptions with a specific HTTP status code.
 */
public class WebException extends RuntimeException {

  /** HTTP status code associated with this exception. */
  private final HttpStatus status;

  /**
   * Constructs a new WebException with the specified HTTP status and message.
   *
   * @param status HTTP status code to associate with this exception.
   * @param message The detail message to associate with this exception.
   */
  public WebException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
