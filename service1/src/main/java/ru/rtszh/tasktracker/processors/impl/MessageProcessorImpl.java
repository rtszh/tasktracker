package ru.rtszh.tasktracker.processors.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.messages.consuming.ReceivedMessage;
import ru.rtszh.tasktracker.processors.MessageProcessor;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    private final Map<ActionType, Consumer<ReceivedMessage>> messageActionMap;

    public MessageProcessorImpl(Map<ActionType, Consumer<ReceivedMessage>> actionMap) {
        this.messageActionMap = actionMap;
    }

    @Override
    public void process(ReceivedMessage receivedMessage) {
        var actionType = receivedMessage.actionType();

        var handler = messageActionMap.get(actionType);

        handler.accept(receivedMessage);
    }
}
