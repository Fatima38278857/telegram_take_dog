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
    private Long chatId;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "passport")
    private Long passport;
    @Column(name = "registeredAt")
    private Timestamp registeredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusUser status;

    public User(Long chatId, String name, String lastName, String firstName, Long phone, Long passport, Timestamp registeredAt, StatusUser status) {
        this.chatId = chatId;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.passport = passport;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public User() {
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
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
        return Objects.equals(chatId, user.chatId) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(phone, user.phone) && Objects.equals(passport, user.passport) && Objects.equals(registeredAt, user.registeredAt) && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, name, lastName, firstName, phone, passport, registeredAt, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone=" + phone +
                ", passport=" + passport +
                ", registeredAt=" + registeredAt +
                ", status=" + status +
                '}';
    }
}

