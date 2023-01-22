package ru.rtszh.tasktracker.telegramclient.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class SendMessageEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishSendMessageEvent(SendMessage message) {
        SendMessageEvent customSpringEvent = new SendMessageEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
