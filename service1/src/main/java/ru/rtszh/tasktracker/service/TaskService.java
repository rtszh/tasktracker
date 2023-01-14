package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.dto.TaskDto;

public interface TaskService {
    String findAllUserTasks(String userLogin);

    String createTask(TaskDto taskDto);

    String deleteTask(TaskDto taskDto);
}
