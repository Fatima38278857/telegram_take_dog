package com.example.telegram_take_dog.repository;



import com.example.telegram_take_dog.model.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {


  //  List<NotificationTask> findAllByDateTimeNotification(LocalDateTime localDateTime);
}