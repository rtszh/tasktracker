package ru.rtszh.tasktracker.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.processors.impl.CreateTaskProcessor;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ProcessorConfig {

    @Bean
    public Map<ActionType, Consumer<Message>> messageActionMap(TaskService taskService) {
        var messageActionMap = new HashMap<ActionType, Consumer<Message>>();

        messageActionMap.put(
                ActionType.CREATE_TASK, new CreateTaskProcessor(taskService)
        );
        return messageActionMap;
    }

}
