package com.example.springconsumer.repo;

import com.example.springconsumer.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



}
