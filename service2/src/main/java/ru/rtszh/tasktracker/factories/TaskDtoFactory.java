package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.Optional;

public class TaskDtoFactory {
    public static TaskDto createTaskDtoFromMessage(Message message) {
        return TaskDto.builder()
                .title(message.getTitle())
                .description(message.getDescription())
                .userLogin(message.getUserLogin())
                .build();
    }
}
