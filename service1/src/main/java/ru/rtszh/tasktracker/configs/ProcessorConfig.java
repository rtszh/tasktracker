package ru.rtszh.tasktracker.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.processors.impl.ReceiveUserTasksProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static ru.rtszh.tasktracker.domain.ActionType.GET_USER_TASKS;

@Configuration
@RequiredArgsConstructor
public class ProcessorConfig {

    @Bean
    public Map<ActionType, Consumer<Message>> messageActionMap() {
        var messageActionMap = new HashMap<ActionType, Consumer<Message>>();

        messageActionMap.put(
                GET_USER_TASKS, new ReceiveUserTasksProcessor()
        );

        return messageActionMap;
    }

}
