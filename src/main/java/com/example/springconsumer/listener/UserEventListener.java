package com.example.springconsumer.listener;

import com.example.springconsumer.model.UserEntity;
import com.example.springconsumer.model.UserPayload;
import com.example.springconsumer.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(
        id = "user_listener",
        topics = "sample_mq",
        containerFactory = "userListenerContainerFactory"
)
public class UserEventListener {

    private final UserRepository repository;

    @KafkaHandler
    void userListener(UserPayload data) {
        //an event comes in the topic, this method is invoked
        //store data in DB
        System.out.println("User Listener: ----> " + data);
        UserEntity userEntity = UserEntity.builder()
                .name(data.getName())
                .email(data.getEmail())
                .build();
        repository.save(userEntity);
    }


    @KafkaHandler(isDefault = true)
    void defaultListener(Object data) {
        //discard event
        System.out.println("User Default invoked.");
    }
}
