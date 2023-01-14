package ru.rtszh.tasktracker.processors;

import ru.rtszh.tasktracker.dto.Message;

public interface MessageProcessor {
    void process(Message message);
}
