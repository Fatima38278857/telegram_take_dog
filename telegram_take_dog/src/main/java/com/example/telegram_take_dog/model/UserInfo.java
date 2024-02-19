package com.example.telegram_take_dog.model;

import jakarta.persistence.*;
import lombok.Builder;


import java.util.Objects;

@Entity(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_info")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surName")
    private String surName;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "passport")
    private String passport;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public UserInfo(Long id, String name, String surName, Long phone, String passport, User user) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.passport = passport;
        this.user = user;
    }

    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) && Objects.equals(name, userInfo.name) && Objects.equals(surName, userInfo.surName) && Objects.equals(phone, userInfo.phone) && Objects.equals(passport, userInfo.passport) && Objects.equals(user, userInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, phone, passport, user);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phone=" + phone +
                ", passport='" + passport + '\'' +
                ", user=" + user +
                '}';
    }
}
