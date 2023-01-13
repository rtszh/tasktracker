package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    String addTask(Task task);

    void deleteTask(TaskDto taskDto);
}
