package com.example.telegram_take_dog.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Dog extends Animal{

    private final Long id;
    private String name;
    private boolean animalWithDisability;

    public Dog(Long id, String name, boolean animalWithDisability) {
        this.id = id;
        this.name = name;
        this.animalWithDisability = animalWithDisability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnimalWithDisability() {
        return animalWithDisability;
    }

    public void setAnimalWithDisability(boolean animalWithDisability) {
        this.animalWithDisability = animalWithDisability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return animalWithDisability == dog.animalWithDisability && Objects.equals(id, dog.id) && Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animalWithDisability);
    }
}
