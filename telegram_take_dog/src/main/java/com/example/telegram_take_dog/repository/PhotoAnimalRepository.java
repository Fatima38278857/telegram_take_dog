package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.PhotoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoAnimalRepository extends JpaRepository<PhotoAnimal, Long> {
}
