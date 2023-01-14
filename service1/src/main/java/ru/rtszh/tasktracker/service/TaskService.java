package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public interface TaskService {
    String findAllUserTasks(String userLogin);

    String createTask(TaskDto taskDto);

    String deleteTask(TaskDto taskDto);
}
