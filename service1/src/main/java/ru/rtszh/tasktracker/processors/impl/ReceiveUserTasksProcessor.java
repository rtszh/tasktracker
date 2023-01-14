package ru.rtszh.tasktracker.processors.impl;

import lombok.extern.slf4j.Slf4j;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.function.Consumer;

@Slf4j
public class ReceiveUserTasksProcessor implements Consumer<Message> {

    @Override
    public void accept(Message message) {
        log.info("message received: {}", message);
    }
}
