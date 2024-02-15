package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 */
public interface DogRepository extends JpaRepository<Dog, Long> {
    /**
     * получить собаки  из БД по id
     */
    Dog getById(Long id);

}

