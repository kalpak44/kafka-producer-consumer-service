package com.example.kafka.producer;

import com.example.kafka.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** KafkaController is responsible for handling HTTP requests related to Kafka operations. */
@RestController
@RequestMapping("/api/kafka")
public class KafkaController implements KafkaControllerApi {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);
  private final KafkaProducerService producerService;

  public KafkaController(KafkaProducerService producerService) {
    this.producerService = producerService;
  }

  /**
   * HTTP POST endpoint for creating a Kafka event. Sends a test message to a Kafka topic and logs
   * the operation.
   *
   * @return ResponseEntity with HTTP status.
   */
  @PostMapping("/createEvent")
  @Override
  public ResponseEntity<Void> createEvent() {
    LOGGER.trace("Send test message");
    producerService.sendMessage(KafkaConfig.TOPIC_NAME, "This is a test message");
    LOGGER.trace("Test message was sent successfully");
    return ResponseEntity.noContent().build();
  }
}
