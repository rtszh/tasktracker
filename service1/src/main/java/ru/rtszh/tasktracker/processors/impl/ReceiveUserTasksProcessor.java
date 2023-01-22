package ru.rtszh.tasktracker.processors.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.dto.messages.consuming.ReceivedMessage;

import java.util.function.Consumer;

@Slf4j
@Component("get-user-tasks")
public class ReceiveUserTasksProcessor implements Consumer<ReceivedMessage> {

    @Override
    public void accept(ReceivedMessage receivedMessage) {
        log.info("message received: {}", receivedMessage);


    }
}
