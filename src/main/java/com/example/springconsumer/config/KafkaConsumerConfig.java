package com.example.springconsumer.config;

import com.example.springconsumer.model.MessagePayload;
import com.example.springconsumer.model.UserPayload;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig() { //this can be a bean
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TYPE_MAPPINGS, "MessagePayload:com.example.springconsumer.model.MessagePayload, UserPayload:com.example.springconsumer.model.UserPayload");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    @Bean
    public ConsumerFactory<String, MessagePayload> messagePayloadConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig()
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessagePayload>> messageListenerContainerFactory(
            ConsumerFactory<String, MessagePayload> messagePayloadConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, MessagePayload> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(messagePayloadConsumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UserPayload> userPayloadConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig()
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserPayload>> userListenerContainerFactory(
            ConsumerFactory<String, UserPayload> userPayloadConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, UserPayload> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(userPayloadConsumerFactory);
        return factory;
    }
}
