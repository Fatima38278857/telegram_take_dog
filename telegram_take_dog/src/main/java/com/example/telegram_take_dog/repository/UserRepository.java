package com.example.telegram_take_dog.repository;


import com.example.telegram_take_dog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
