package ru.rtszh.tasktracker.telegramclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.properties.TelegramBotProperties;
import ru.rtszh.tasktracker.telegramclient.events.SendMessageEvent;

import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class TelegramClient extends TelegramLongPollingBot {
    private final TelegramBotProperties botProperties;
    private final MessageSender messageSender;
    ExecutorService executorService;

    public TelegramClient(TelegramBotProperties botProperties, MessageSender messageSender, ExecutorService executorService) {
        this.botProperties = botProperties;
        this.messageSender = messageSender;
        this.executorService = executorService;
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }

    @Override
    public String getBotUsername() {
        return botProperties.getBotUserName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        executorService.submit(
                () -> messageSender.sendMessage(update)
        );

    }

    @EventListener
    public void sendMessage(SendMessageEvent sendMessageEvent) {
        try {
            execute(sendMessageEvent.getMessage());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
