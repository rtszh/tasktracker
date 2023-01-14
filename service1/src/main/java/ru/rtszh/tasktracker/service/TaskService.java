package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.dto.TaskToCreateDto;
import ru.rtszh.tasktracker.dto.TaskToDeleteDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;

public interface TaskService {
    String findAllUserTasks(String userLogin);

    String createTask(TaskToCreateDto taskToCreateDto);

    String updateTask(TaskToUpdateDto taskToUpdateDto);

    String deleteTask(TaskToDeleteDto taskToDeleteDto);


}
