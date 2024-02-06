package com.example.telegram_take_dog.model;

import javax.persistence.Entity;
import java.util.Objects;


@Entity
public abstract class Animal {

    private Long id;
    private String name;
    private boolean animalWithDisability;

    public Animal(Long id, String name, boolean animalWithDisability) {
        this.id = id;
        this.name = name;
        this.animalWithDisability = animalWithDisability;
    }
    public Animal() {
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
        Animal animal = (Animal) o;
        return animalWithDisability == animal.animalWithDisability && Objects.equals(id, animal.id) && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animalWithDisability);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", animalWithDisability=" + animalWithDisability +
                '}';
    }
}


