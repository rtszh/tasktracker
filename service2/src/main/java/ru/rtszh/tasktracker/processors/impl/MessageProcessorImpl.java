package ru.rtszh.tasktracker.processors.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.processors.MessageProcessor;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    private final Map<ActionType, Consumer<Message>> messageActionMap;


    public MessageProcessorImpl(Map<ActionType, Consumer<Message>> actionMap) {
        this.messageActionMap = actionMap;
    }

    @Override
    public void process(Message message) {
        var actionType = message.actionType();

        var handler = messageActionMap.get(actionType);

        handler.accept(message);
    }
}
