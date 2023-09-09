package com.example.kafka.producer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/** KafkaControllerApi defines the API for Kafka-related operations. */
public interface KafkaControllerApi {

  /**
   * Creates an event in Apache Kafka.
   *
   * <p>This operation is tagged as "event creation example" and has two possible responses:
   *
   * <ul>
   *   <li>204: Event was created successfully
   *   <li>500: Exception occurred
   * </ul>
   *
   * @return ResponseEntity with HTTP status.
   */
  @Tag(name = "event creation example")
  @Operation(operationId = "event creation example", summary = "create an event in apache kafka")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Event was created successfully"),
        @ApiResponse(responseCode = "500", description = "Exception occurred")
      })
  ResponseEntity<Void> createEvent();
}
