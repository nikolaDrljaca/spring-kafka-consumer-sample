package com.example.springconsumer.listener;

import com.example.springconsumer.model.MessagePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(
        id = "message_listener",
        topics = "sample_mq"
        //containerFactory = "messageListenerContainerFactory",
        //groupId = "group_id"
)
public class MessageEventListener {

    @KafkaHandler
    void messageListener(MessagePayload event) {
        //an event comes in the topic, this method is invoked
        System.out.println("Message Listener: ----> " + event);
    }

    @KafkaHandler(isDefault = true)
    void defaultHandler(Object data) {
        //discard event
        System.out.println("Default invoked.");
    }
}
