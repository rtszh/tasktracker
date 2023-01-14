package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.dto.TaskToCreateDto;
import ru.rtszh.tasktracker.dto.TaskToDeleteDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

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
    public String createTask(TaskToCreateDto taskToCreateDto) {
        return messageSender.sendMessage(createMessageFromCreateTaskDto(taskToCreateDto, CREATE_TASK));
    }

    @Override
    public String updateTask(TaskToUpdateDto taskToUpdateDto) {
        return messageSender.sendMessage(createMessageFromUpdateTaskDto(taskToUpdateDto, UPDATE_TASK));
    }

    @Override
    public String deleteTask(TaskToDeleteDto taskToDeleteDto) {
        return messageSender.sendMessage(createMessageFromDeleteTaskDto(taskToDeleteDto, DELETE_TASK));
    }
}
