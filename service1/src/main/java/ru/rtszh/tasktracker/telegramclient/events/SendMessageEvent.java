package ru.rtszh.tasktracker.telegramclient.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageEvent extends ApplicationEvent {
    @Getter
    private final SendMessage message;

    public SendMessageEvent(Object source, SendMessage message) {
        super(source);
        this.message = message;
    }
}
