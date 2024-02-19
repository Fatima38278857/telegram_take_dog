package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInRepository extends JpaRepository<UserInfo, Long>
{
}
