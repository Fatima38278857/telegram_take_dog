package com.example.telegram_take_dog.repository;


import com.example.telegram_take_dog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

