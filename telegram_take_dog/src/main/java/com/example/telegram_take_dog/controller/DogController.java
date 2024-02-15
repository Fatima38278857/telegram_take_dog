package com.example.telegram_take_dog.controller;

import com.example.telegram_take_dog.model.Dog;
import com.example.telegram_take_dog.service.DogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
/**
 * Добавление и получение собвки
 * @route POST /auth/sign-up
 * @group Авторизация (пользователь) - Функции для авторизации пользователя
 * @param  - Входные данные
 * @returns - Авторизационные данные пользователя
 * @returns  - Ошибка запроса
 */
@RestController
@RequestMapping("Dog")
@Tag(name = "Собаки")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("{id}")
    public Dog getDogId(@PathVariable Long id){
        return dogService.read(id);
    }
    @PostMapping
    public Dog post(@RequestBody Dog dog) {
        return dogService.create(dog);
    }
}

