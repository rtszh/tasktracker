package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;

public class TaskDtoFactory {
    public static Task createTaskFromTaskDto(TaskDto taskDto, ActionType actionType) {
        return Task.builder()
                .taskTitle(taskDto.taskTitle())
                .taskDescription(taskDto.taskDescription())
                .userLogin(taskDto.userLogin())
                .actionType(actionType)
                .build();
    }
}
