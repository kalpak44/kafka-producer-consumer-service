package com.example.kafka.producer;

import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/** KafkaProducerService is responsible for sending messages to Kafka topics. */
@Service
public class KafkaProducerService {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  /**
   * Sends a message to a specific Kafka topic.
   *
   * @param topic The name of the Kafka topic to which the message will be sent.
   * @param message The message to be sent.
   */
  public void sendMessage(String topic, String message) {
    final Message<String> messageWithHeaders =
        MessageBuilder.withPayload(message)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
            .setHeader("myCustomHeader", "customHeaderValue")
            .build();

    kafkaTemplate.send(messageWithHeaders);
  }
}
