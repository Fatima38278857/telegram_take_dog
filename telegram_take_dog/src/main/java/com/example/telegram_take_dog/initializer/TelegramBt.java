package com.example.telegram_take_dog.initializer;

import com.example.telegram_take_dog.configuration.TelegramBotConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import static com.example.telegram_take_dog.enumm.Command.*;


@Component
public class TelegramBt extends TelegramLongPollingBot {

    final TelegramBotConfiguration configuration;

    public TelegramBt(TelegramBotConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @Override
    public String getBotUsername() {
        return "Dog";
    }

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");

            }
        }
    }

    public void startCommandReceived(long chatId, String text) {
        String answer = String.valueOf(GREETINGS_MESSAGE);
        sendMessage(chatId, answer);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param textToSend Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }
}



