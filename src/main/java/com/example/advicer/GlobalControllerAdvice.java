package com.example.advicer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalControllerAdvice is a central point for handling exceptions across all controllers.
 *
 * <p>This class is annotated with @ControllerAdvice to make it a global exception handler. The
 * methods in this class are annotated with @ExceptionHandler to handle specific types of
 * exceptions.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

  /**
   * Handles all types of Exception.
   *
   * <p>This method catches any Exception thrown by controllers, logs the exception, and returns a
   * ResponseEntity containing an ErrorResponse object and the appropriate HTTP status.
   *
   * @param e The caught Exception.
   * @return A ResponseEntity with the ErrorResponse and appropriate HTTP status.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception e) {
    final var status =
        e instanceof WebException
            ? ((WebException) e).getStatus()
            : HttpStatus.INTERNAL_SERVER_ERROR;
    LOGGER.warn(e.getMessage(), e);
    var errorResponse = new ErrorResponse(LocalDateTime.now(ZoneOffset.UTC), e.getMessage());
    return new ResponseEntity<>(errorResponse, status);
  }

  /**
   * ErrorResponse is a record that holds the timestamp and message of an error.
   *
   * <p>This record is used in the ResponseEntity returned by the exception handler.
   */
  public record ErrorResponse(LocalDateTime timestamp, String message) {}
}
