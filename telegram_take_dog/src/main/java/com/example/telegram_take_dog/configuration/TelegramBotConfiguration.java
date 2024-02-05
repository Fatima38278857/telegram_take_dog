package com.example.telegram_take_dog.configuration;


import com.example.telegram_take_dog.repository.UserRepository;
import com.example.telegram_take_dog.service.ImplementationBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



@Configuration
@ComponentScan(basePackages = "com.example.telegram_take_dog.repository")
public class TelegramBotConfiguration {


    @Bean
    public TelegramBotsApi bot(ImplementationBot telegramBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot);
        return telegramBotsApi;

    }


}
