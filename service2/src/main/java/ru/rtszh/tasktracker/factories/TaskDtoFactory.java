package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

public class TaskDtoFactory {
    public static TaskDto createTaskDtoFromMessage(Message message) {
        return TaskDto.builder()
                .title(message.title())
                .description(message.description())
                .userLogin(message.userLogin())
                .build();
    }
}
