package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;

import static ru.rtszh.tasktracker.domain.ActionType.*;
import static ru.rtszh.tasktracker.factories.MessageFactory.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final MessageSender messageSender;

    public TaskServiceImpl(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public String findAllUserTasks(String userLogin) {

        return messageSender.sendMessage(createMessageFromUserLogin(userLogin, GET_USER_TASKS));
    }

    @Override
    public String createTask(TaskDto taskDto) {
        return messageSender.sendMessage(createMessageFromTaskDto(taskDto, CREATE_TASK));
    }

    @Override
    public String deleteTask(TaskDto taskDto) {
        return messageSender.sendMessage(createMessageFromTaskDto(taskDto, DELETE_TASK));
    }
}
