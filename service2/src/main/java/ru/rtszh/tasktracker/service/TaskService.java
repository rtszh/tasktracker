package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    List<TaskDto> findAllTasksByUser(String userLogin);

    void addTask(TaskDto taskDto);

    void updateTask(TaskToUpdateDto taskDto);

    void deleteTask(TaskDto taskDto);
}
