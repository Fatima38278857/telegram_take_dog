package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
