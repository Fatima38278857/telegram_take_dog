package com.example.telegram_take_dog.configuration;


import org.springframework.beans.factory.annotation.Value;;
import org.springframework.context.annotation.Configuration;


import java.util.Objects;

@Configuration
public class TelegramBotConfiguration  { // Класс отвечает за  Инициолизация бота

    @Value("${bot.token}")
    String token;

    public TelegramBotConfiguration(String token) {
        this.token = token;
    }

    public TelegramBotConfiguration() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramBotConfiguration that = (TelegramBotConfiguration) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
