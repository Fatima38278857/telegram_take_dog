package com.example.telegram_take_dog.model;

import com.example.telegram_take_dog.enumm.StatusUser;
import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 */
@Data
@Entity(name = "users")
public class User { // Пользователь

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_user")
    private Long id;
    @Column(name = "chatId")
    private Long chatId;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "registeredAt")
    private Timestamp registeredAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusUser status;

    public User(Long id, Long chatId, String name, String lastName, String firstName, Timestamp registeredAt, StatusUser status) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }


    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(chatId, user.chatId) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(registeredAt, user.registeredAt)  && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, name, lastName, firstName, registeredAt, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", Имя ='" + name + '\'' +
                ", Фамилия ='" + lastName + '\'' +
                ", Отчество ='" + firstName + '\'' +
                ", registeredAt=" + registeredAt +
                ", status=" + status +
                '}';
    }
}

