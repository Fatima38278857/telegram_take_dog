package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalRepository extends JpaRepository<Animal, Long>{

}