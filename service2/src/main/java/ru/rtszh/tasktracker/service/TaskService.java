package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.DtoToCreateTask;
import ru.rtszh.tasktracker.dto.DtoToDeleteTask;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    List<TaskDto> findAllTasksByUser(String chatId);

    void addTask(DtoToCreateTask taskDto);

    void updateTask(TaskToUpdateDto taskDto);

    void deleteTask(DtoToDeleteTask taskDto);
//    void deleteTask(TaskDto taskDto);
}
