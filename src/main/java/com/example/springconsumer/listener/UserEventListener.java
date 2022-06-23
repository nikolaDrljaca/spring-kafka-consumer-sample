package com.example.springconsumer.listener;

import com.example.springconsumer.model.UserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(
        id = "user_listener",
        topics = "sample_mq"
        //containerFactory = "userListenerContainerFactory",
        //groupId = "group_id"
)
public class UserEventListener {

    @KafkaHandler
    void userListener(UserPayload data) {
        //an event comes in the topic, this method is invoked
        //store data in DB
        System.out.println("User Listener: ----> " + data);

    }


    @KafkaHandler(isDefault = true)
    void defaultHandler(Object data) {
        //discard event
        System.out.println("Default invoked." + data);
    }
}
