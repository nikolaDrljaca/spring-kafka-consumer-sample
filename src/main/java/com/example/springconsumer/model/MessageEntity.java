package com.example.springconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @Builder
    public MessageEntity(String content, UserEntity user) {
        this.content = content;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}