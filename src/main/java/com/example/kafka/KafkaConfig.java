package com.example.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * KafkaConfig is responsible for setting up the Kafka producer and consumer configurations.
 *
 * <p>This class is annotated with @Configuration and @EnableKafka to indicate that it's a Spring
 * configuration class and to enable Kafka-related functionalities.
 */
@Configuration
@EnableKafka
public class KafkaConfig {

  /** Name of the Kafka topic. */
  public static final String TOPIC_NAME = "my_topic";

  /** ID of the Kafka consumer group. */
  public static final String GROUP_ID = "my_service_group";

  /** Address of the Kafka bootstrap server. */
  private final String bootstrapAddress;

  /**
   * Constructs a KafkaConfig with the specified bootstrap address.
   *
   * @param bootstrapAddress Address of the Kafka bootstrap server.
   */
  public KafkaConfig(@Value(value = "${spring.kafka.bootstrap-servers}") String bootstrapAddress) {
    this.bootstrapAddress = bootstrapAddress;
  }

  /**
   * Configures and produces a ProducerFactory bean.
   *
   * @return A ProducerFactory for creating Kafka producers.
   */
  @Bean
  public ProducerFactory<String, String> producerFactory() {
    final Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Configures and produces a ConsumerFactory bean.
   *
   * @return A ConsumerFactory for creating Kafka consumers.
   */
  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    final Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  /**
   * Configures and produces a ConcurrentKafkaListenerContainerFactory bean.
   *
   * @return A ConcurrentKafkaListenerContainerFactory for creating Kafka listeners.
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    final ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    return factory;
  }

  /**
   * Configures and produces a KafkaTemplate bean.
   *
   * @return A KafkaTemplate for producing messages.
   */
  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  /**
   * Configures and produces a NewTopic bean.
   *
   * @return A NewTopic for creating the Kafka topic.
   */
  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(TOPIC_NAME).partitions(1).replicas(1).build();
  }
}
