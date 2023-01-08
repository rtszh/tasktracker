package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    Task addTask(TaskDto taskDto);

    void deleteTask(TaskDto taskDto);
}