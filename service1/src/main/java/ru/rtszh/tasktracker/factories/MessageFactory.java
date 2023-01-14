package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.TaskToCreateDto;
import ru.rtszh.tasktracker.dto.TaskToDeleteDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;
import ru.rtszh.tasktracker.dto.messages.producing.TaskToCreateMessage;
import ru.rtszh.tasktracker.dto.messages.producing.TaskToDeleteMessage;
import ru.rtszh.tasktracker.dto.messages.producing.TaskToUpdateMessage;
import ru.rtszh.tasktracker.dto.messages.producing.UserTasksMessage;

public class MessageFactory {
    public static TaskToCreateMessage createMessageFromCreateTaskDto(TaskToCreateDto taskDto, ActionType actionType) {
        return TaskToCreateMessage.builder()
                .userLogin(taskDto.userLogin())
                .taskTitle(taskDto.taskTitle())
                .taskDescription(taskDto.taskDescription())
                .actionType(actionType)
                .build();
    }

    public static TaskToUpdateMessage createMessageFromUpdateTaskDto(TaskToUpdateDto taskDto, ActionType actionType) {
        return TaskToUpdateMessage.builder()
                .userLogin(taskDto.userLogin())
                .taskTitle(taskDto.taskTitle())
                .newTaskTitle(taskDto.newTaskTitle())
                .taskDescription(taskDto.taskDescription())
                .taskOrderNumber(taskDto.taskOrderNumber())
                .actionType(actionType)
                .build();
    }

    public static UserTasksMessage createMessageFromUserLogin(String userLogin, ActionType actionType) {
        return UserTasksMessage.builder()
                .userLogin(userLogin)
                .actionType(actionType)
                .build();
    }

    public static TaskToDeleteMessage createMessageFromDeleteTaskDto(TaskToDeleteDto taskDto, ActionType actionType) {
        return TaskToDeleteMessage.builder()
                .taskTitle(taskDto.taskTitle())
                .userLogin(taskDto.userLogin())
                .actionType(actionType)
                .build();
    }
}
