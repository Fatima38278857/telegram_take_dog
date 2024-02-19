package com.example.telegram_take_dog.service;

import com.example.telegram_take_dog.exception.DogNotFoundException;
import com.example.telegram_take_dog.model.Dog;
import com.example.telegram_take_dog.repository.DogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/** Class DogService
 * реалижует CRUD-методы для
 * создания,
 * чтения,
 * изменения и
 * удаления сущностей
 *
 */
@Service
public class DogService {
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }
    //  CRUD-методы для создания, чтения, изменения и удаления сущностей.

    /**
     *
     * @param dog
     * @return
     */
    public Dog create(Dog dog) {
        return dogRepository.save(dog);
    }

    /**
     *
     * @param id
     * @return
     */
    public Dog read(long id) {
        return dogRepository.findById(id).orElseThrow(() ->
                new DogNotFoundException("Такой собаки  нет"));

    }

}
