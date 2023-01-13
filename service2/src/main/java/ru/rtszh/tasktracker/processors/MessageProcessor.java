package ru.rtszh.tasktracker.processors;

import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

public interface MessageProcessor {
    void process(Message message);
}
