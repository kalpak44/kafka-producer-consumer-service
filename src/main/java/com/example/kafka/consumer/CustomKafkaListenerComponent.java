package com.example.kafka.consumer;

import com.example.kafka.KafkaConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * CustomKafkaListenerComponent is responsible for listening to Kafka topics and processing
 * messages.
 */
@Component
public class CustomKafkaListenerComponent {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomKafkaListenerComponent.class);

  /**
   * Listens to messages from a specific Kafka group and topic.
   *
   * <p>This method will be invoked whenever a new message is published to the configured Kafka
   * topic. It processes the message, logs its content, and acknowledges the message.
   *
   * @param consumerRecord The record that contains the received message and its metadata.
   * @param consumer The Kafka consumer that allows for manual offset management.
   * @param acknowledgment The acknowledgment object used for manual commits.
   * @throws InterruptedException If the thread is interrupted during sleep.
   */
  @KafkaListener(
      groupId = KafkaConfig.GROUP_ID,
      topics = {KafkaConfig.TOPIC_NAME})
  public void listenGroup(
      ConsumerRecord<String, String> consumerRecord,
      Consumer<String, String> consumer,
      Acknowledgment acknowledgment)
      throws InterruptedException {

    // Simulate some processing time
    Thread.sleep(10_000);

    // Extract the received message and headers
    final String message = consumerRecord.value();
    final Headers headers = consumerRecord.headers();

    // Log the received message
    LOGGER.info("Received Message: " + message);

    // Acknowledge the message
    acknowledgment.acknowledge();
  }
}
