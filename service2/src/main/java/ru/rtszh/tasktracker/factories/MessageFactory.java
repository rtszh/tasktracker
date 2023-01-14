package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.domain.ActionType;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

public class MessageFactory {
    public static Message createMessageFromTaskDto(TaskDto taskDto, ActionType actionType) {
        return Message.builder()
                .title(taskDto.title())
                .description(taskDto.description())
                .userLogin(taskDto.userLogin())
                .actionType(actionType)
                .build();
    }
}
