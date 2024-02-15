package com.example.telegram_take_dog.model;



import jakarta.persistence.Entity;

import java.util.Objects;

/**
 *  Клас наследник Animal
 *
 */
@Entity
public class Dog extends Animal{

    private int age; // Возрост
    private String breed; // Порода

    public Dog(Long id, String name, boolean animalWithDisability, int old, String breed) {
        super(id, name, animalWithDisability);
        this.age = old;
        this.breed = breed;
    }

    public Dog() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return age == dog.age && Objects.equals(breed, dog.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, breed);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "old=" + age +
                ", breed='" + breed + '\'' +
                '}';
    }
}
