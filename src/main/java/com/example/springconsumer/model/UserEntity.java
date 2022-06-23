package com.example.springconsumer.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;

    @Builder
    public UserEntity(String name, String email) {

    }

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.REMOVE}
    )
    @EqualsAndHashCode.Exclude
    private Collection<MessageEntity> messages = new HashSet<>();
}
