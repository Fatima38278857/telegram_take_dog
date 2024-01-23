package com.example.telegram_take_dog.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotificationService { // Уведомление


//    private final NotificationTaskRepository notificationTaskRepository;
//    private final TelegramBot telegramBot;
//
//    public NotificationService(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
//        this.notificationTaskRepository = notificationTaskRepository;
//        this.telegramBot = telegramBot;
//    }


//    public void notification() {
//        notificationTaskRepository.findAllByDateTimeNotification(LocalDateTime
//                .now().truncatedTo(ChronoUnit.MINUTES)).forEach(notificationTask -> {
//            SendMessage message = new SendMessage(notificationTask.getChatId(), notificationTask.getContentNotification());
//            telegramBot.execute(message);
//        });
    }
//}
