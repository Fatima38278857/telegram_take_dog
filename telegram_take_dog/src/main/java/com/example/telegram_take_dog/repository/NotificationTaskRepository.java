package com.example.telegram_take_dog.repository;



import com.example.telegram_take_dog.model.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {


  //  List<NotificationTask> findAllByDateTimeNotification(LocalDateTime localDateTime);
}