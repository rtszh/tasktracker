package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;

import static ru.rtszh.tasktracker.domain.ActionType.*;
import static ru.rtszh.tasktracker.factories.TaskDtoFactory.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final MessageSender messageSender;

    public TaskServiceImpl(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public List<Task> findAllTasks() {
        return null;
    }

    @Override
    public String createTask(TaskDto taskDto) {
        return messageSender.sendMessage(createTaskFromTaskDto(taskDto, CREATE_TASK));
    }

    @Override
    public String deleteTask(TaskDto taskDto) {
        return messageSender.sendMessage(createTaskFromTaskDto(taskDto, DELETE_TASK));
    }
}
