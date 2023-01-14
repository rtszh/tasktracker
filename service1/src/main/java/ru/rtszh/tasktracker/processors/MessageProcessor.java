package ru.rtszh.tasktracker.processors;

import ru.rtszh.tasktracker.dto.messages.consuming.ReceivedMessage;

public interface MessageProcessor {
    void process(ReceivedMessage receivedMessage);
}
