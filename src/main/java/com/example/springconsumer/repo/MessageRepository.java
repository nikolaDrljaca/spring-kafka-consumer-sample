package com.example.springconsumer.repo;

import com.example.springconsumer.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {



}
