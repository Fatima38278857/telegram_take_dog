package com.example.telegram_take_dog.service;

import com.example.telegram_take_dog.enumm.StatusUser;
import com.example.telegram_take_dog.exception.NullNotFoundException;
import com.example.telegram_take_dog.model.User;
import com.example.telegram_take_dog.model.UserInfo;
import com.example.telegram_take_dog.repository.UserInRepository;
import com.example.telegram_take_dog.repository.UserRepository;
//import jdk.internal.joptsimple.internal.Messages;
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

/**
 *
 */
@Slf4j
@Component
public class ImplementationBot extends TelegramLongPollingBot {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInRepository userInRepository;

    private boolean stub = false;
    private final Logger logger = LoggerFactory.getLogger(ImplementationBot.class);
    @Value("${bot.name}")
    private String nameBot;


    public ImplementationBot(@Value("${bot.token}") String botToken) {
        super(botToken);
        List<BotCommand> listCommands = new ArrayList<>();
        listCommands.add(new BotCommand("/start", "Запуск бота"));
        listCommands.add(new BotCommand("/recommend", "рекомендации"));
        listCommands.add(new BotCommand("/refusal", "список причины отказа"));
        listCommands.add(new BotCommand("/volunteer", "связатся с волонтером"));
        listCommands.add(new BotCommand("/rules", "о приюте"));
        listCommands.add(new BotCommand("/adopt_dog", "усыновить собаку"));
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (stub) {
                stub = false;
                // name, surName, phon, passport
//                if (!messageText.matches("\\w+, \\w+, \\d+, \\w+(\\.)?")) {
//                    throw new RuntimeException("dvfkgdkfgerg");
//
//                }
                String[] strings = messageText.split(", ");
                UserInfo userInfo = new UserInfo();
                userInfo.setName(strings[0]);
                userInfo.setSurName(strings[1]);
                userInfo.setPhone(Long.parseLong(strings[2]));
                userInfo.setPassport(strings[3]);
                userInRepository.save(userInfo);
                User user = userRepository.findById(chatId).orElseThrow(NullNotFoundException::new);
                user.setStatus(StatusUser.PROBATION);
                userRepository.save(user);
                sendMessage(chatId, REPORT_NOTICE.getMessageText());
                return;
            }
// TODO Запрос на смену статуса в Repository

            switch (messageText) {
                case "/start":
                    registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/recommend":
                    commandRecommend(chatId, update.getMessage().getChat().toString());
                    break;
                case "/rules":
                    commandInfoShelter(chatId, INFO_SHELTER.getMessageText());
                    break;
                case "/volunteer":
                    commandVolunteer(chatId, VOLUNTEER.getMessageText());
                    break;
                case "/refusal":
                    commandRefusal(chatId, REFUSAL.getMessageText());
                    break;
                case "/adopt_dog":
                    stub = true;
                    sendMessage(chatId, DATA_IN_FORMAT.getMessageText());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");
            }
        }
    }


    /**
     * Метод для определения, зарегестрирован ли пользователь
     *
     * @param msg Данные о пользователе
     */
    private void registerUser(Message msg) {
        if (userRepository.findById(msg.getChatId()).isEmpty()) {

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();

            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setName(chat.getUserName());
            user.setStatus(StatusUser.POTENTIAL);
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            logger.info("user saved: " + user);
        }

    }


    /**
     * @param chatId
     * @param text
     */
    public void commandRefusal(long chatId, String text) {
        String infoRefusal = String.valueOf(REFUSAL.getMessageText());
        sendMessage(chatId, infoRefusal);
    }

    /**
     * @param chatId
     * @param text
     */
    public void commandVolunteer(long chatId, String text) {
        String infoVolunteer = String.valueOf(VOLUNTEER.getMessageText());
        sendMessage(chatId, infoVolunteer);
    }


    /**
     * @param chatId
     * @param text
     */
    public void commandInfoShelter(long chatId, String text) {
        String infoShelter = String.valueOf(INFO_SHELTER.getMessageText());
        sendMessage(chatId, infoShelter);
    }

    /**
     * @param chatId
     * @param text
     */
    public void startCommandReceived(long chatId, String text) {
        String answer = String.valueOf(GREETINGS_MESSAGE.getMessageText());
        sendMessage(chatId, answer);
    }

    private void commandRecommend(long chatId, String text) {
        String recommend = String.valueOf(RECMMEND.getMessageText());
        sendMessage(chatId, recommend);
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



