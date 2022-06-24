package com.example.springconsumer.listener;

import com.example.springconsumer.model.MessageEntity;
import com.example.springconsumer.model.MessagePayload;
import com.example.springconsumer.model.UserEntity;
import com.example.springconsumer.repo.MessageRepository;
import com.example.springconsumer.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(
        id = "message_listener",
        topics = "sample_mq",
        containerFactory = "messageListenerContainerFactory"
)
public class MessageEventListener {

    private final MessageRepository repository;
    private final UserRepository userRepository;

    @KafkaHandler
    void messageListener(MessagePayload event) {
        //an event comes in the topic, this method is invoked
        System.out.println("Message Listener: ----> " + event);
        UserEntity userEntity = userRepository.findById(Long.valueOf(event.userId))
                .orElseThrow(() -> new RuntimeException("User with " + event.getUserId() + " not found."));

        MessageEntity entity = MessageEntity.builder()
                .content(event.content)
                .user(userEntity)
                .build();

        repository.save(entity);
    }

    @KafkaHandler(isDefault = true)
    void defaultListener(Object data) {
        //discard event
        System.out.println("Message Default invoked.");
    }
}
