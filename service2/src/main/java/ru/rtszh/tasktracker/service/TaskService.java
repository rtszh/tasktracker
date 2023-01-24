package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.DtoToCreateTask;
import ru.rtszh.tasktracker.dto.DtoToDeleteTask;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAllTasksByUser(String chatId);

    void addTask(DtoToCreateTask taskDto);

    void deleteTask(DtoToDeleteTask taskDto);
}
