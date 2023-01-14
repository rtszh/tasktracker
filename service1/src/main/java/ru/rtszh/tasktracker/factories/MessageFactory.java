package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

public class MessageFactory {
    public static Message createMessageFromTaskDto(TaskDto taskDto, ActionType actionType) {
        return Message.builder()
                .taskTitle(taskDto.taskTitle())
                .taskDescription(taskDto.taskDescription())
                .userLogin(taskDto.userLogin())
                .actionType(actionType)
                .build();
    }

    public static Message createMessageFromUserLogin(String userLogin, ActionType actionType) {
        return Message.builder()
                .userLogin(userLogin)
                .actionType(actionType)
                .build();
    }
}
