package com.example.telegram_take_dog.scheduled;

import com.example.telegram_take_dog.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class NotificationPlan {

    private final NotificationService notificationService;

    public NotificationPlan(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


//    @Scheduled(fixedDelay = 60000)
//    public void sendNotification() {
//        notificationService.notification();
//    }
}

