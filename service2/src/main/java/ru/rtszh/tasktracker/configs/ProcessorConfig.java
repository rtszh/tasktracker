package ru.rtszh.tasktracker.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.processors.impl.CreateTaskProcessor;
import ru.rtszh.tasktracker.processors.impl.DeleteTaskProcessor;
import ru.rtszh.tasktracker.processors.impl.GetTasksByUserProcessor;
import ru.rtszh.tasktracker.processors.impl.UpdateTaskProcessor;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static ru.rtszh.tasktracker.domain.ActionType.*;

@Configuration
@RequiredArgsConstructor
public class ProcessorConfig {

    @Bean
    public Map<ActionType, Consumer<Message>> messageActionMap(TaskService taskService, MessageSender messageSender) {
        var messageActionMap = new HashMap<ActionType, Consumer<Message>>();

        messageActionMap.put(
                CREATE_TASK, new CreateTaskProcessor(taskService)
        );

        messageActionMap.put(
                UPDATE_TASK, new UpdateTaskProcessor(taskService)
        );

        messageActionMap.put(
                DELETE_TASK, new DeleteTaskProcessor(taskService)
        );

        messageActionMap.put(
                GET_USER_TASKS, new GetTasksByUserProcessor(taskService, messageSender)
        );

        return messageActionMap;
    }

}
