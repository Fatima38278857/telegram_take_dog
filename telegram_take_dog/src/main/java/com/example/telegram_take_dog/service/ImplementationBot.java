package com.example.telegram_take_dog.service;

import com.example.telegram_take_dog.model.User;
import com.example.telegram_take_dog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.example.telegram_take_dog.enumm.Command.*;

@Slf4j
@Component
public class ImplementationBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(ImplementationBot.class);
    @Value("${bot.name}")
    private String nameBot;
    public ImplementationBot(@Value("${bot.token}") String botToken) {
        super(botToken);
        List<BotCommand> listCommands = new ArrayList<>();
        listCommands.add(new BotCommand("/start", "Запуск бота"));
        listCommands.add(new BotCommand("/recommendations1", "транспортировке животного"));
        listCommands.add(new BotCommand("/recommendations2", "транспортировке"));
        listCommands.add(new BotCommand("/recommendations3", "обустройству дома для щенка"));
        listCommands.add(new BotCommand("/recommendations4", "обустройству дома для взрослого животного"));
        listCommands.add(new BotCommand("/recommendations5", "обустройству дома для животного с ограниченными возможностями"));
        listCommands.add(new BotCommand("/afmfgmbm", "советы кинолога по первичному общению"));
        listCommands.add(new BotCommand("/recommendations6", "для дальнейшего обращения к ним."));
        listCommands.add(new BotCommand("/refusal", "список причины отказа"));
        listCommands.add(new BotCommand("/volunteer", "связатся с волонтером"));
        listCommands.add(new BotCommand("/rules", "о приюте"));
        try {
            this.execute(new SetMyCommands(listCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        // System.out.println(update.getMessage().getText());
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                   // registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");

            }
        }
    }

//    /**
//     * Метод для определения, зарегестрирован ли пользователь
//     *
//     * @param msg Данные о пользователе
//     */
//    private void registerUser(Message msg) {
//        if (userRepository.findById(msg.getChatId()).isEmpty()) {
//
//            var chatId = msg.getChatId();
//            var chat = msg.getChat();
//
//            User user = new User();
//
//            user.setChatId(chatId);
//            user.setFirstName(chat.getFirstName());
//            user.setLastName(chat.getLastName());
//            user.setUserName(chat.getUserName());
//            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
//
//            userRepository.save(user);
//            logger.info("user saved: " + user);
//        }

 //   }

    public void startCommandReceived(long chatId, String text) {
        String answer = String.valueOf(GREETINGS_MESSAGE.getMessageText());
        sendMessage(chatId, answer);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId     id чата
     * @param textToSend Строка, которую необходимот отправить в качестве сообщения.
     */
    public void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Отправить сообщение не удалось" + textToSend);
        }
    }


    @Override
    public String getBotUsername() {
        return nameBot;
    }
}



