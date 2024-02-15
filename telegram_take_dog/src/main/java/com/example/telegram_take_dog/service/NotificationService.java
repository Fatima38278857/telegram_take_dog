package com.example.telegram_take_dog.service;

import com.example.telegram_take_dog.repository.NotificationTaskRepository;
import com.example.telegram_take_dog.repository.UserRepository;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 *
 */
@Service
@AllArgsConstructor
/**
 * Сервер для реолизации
 * бизне- логики шедулера
 */
public class NotificationService { // Уведомление

//    private UserRepository userRepository;
//
//
//    private final NotificationTaskRepository notificationTaskRepository;
//    private final TelegramBot telegramBot;
//
//    public NotificationService(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
//       this.notificationTaskRepository = notificationTaskRepository;
//        this.telegramBot = telegramBot;
//    }
//
//
//    public void notification() {
//        notificationTaskRepository.findAllByDateTimeNotification(LocalDateTime
//                .now().truncatedTo(ChronoUnit.MINUTES)).forEach(notificationTask -> {
//            SendMessage message = new SendMessage(notificationTask.getChatId(), notificationTask.getContentNotification());
//            telegramBot.execute(message);
//        });
//    }
}
