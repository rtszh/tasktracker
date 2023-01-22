package ru.rtszh.tasktracker.processors.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.rtszh.tasktracker.dto.messages.consuming.ReceivedMessage;
import ru.rtszh.tasktracker.processors.MessageProcessor;
import ru.rtszh.tasktracker.processors.TextFormatProcessor;
import ru.rtszh.tasktracker.telegramclient.events.SendMessageEventPublisher;

import java.util.stream.Collectors;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    private final SendMessageEventPublisher sendMessageEventPublisher;
    private final TextFormatProcessor textFormatProcessor;

    public MessageProcessorImpl(SendMessageEventPublisher sendMessageEventPublisher,
                                TextFormatProcessor textFormatProcessor) {
        this.sendMessageEventPublisher = sendMessageEventPublisher;
        this.textFormatProcessor = textFormatProcessor;
    }

    @Override
    public void process(ReceivedMessage receivedMessage) {

        SendMessage sendMessage = createMessage(receivedMessage);

        sendMessageEventPublisher.publishSendMessageEvent(sendMessage);

    }

    private SendMessage createMessage(ReceivedMessage receivedMessage) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(receivedMessage.chatId());

        String text = textFormatProcessor.getTextToSend(receivedMessage.taskDtoList());

        sendMessage.setText(text);

        return sendMessage;
    }
}
