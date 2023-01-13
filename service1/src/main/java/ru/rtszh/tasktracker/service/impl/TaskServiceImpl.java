package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;

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
    public String addTask(Task task) {
        return messageSender.sendMessage(task);
    }

    @Override
    public void deleteTask(TaskDto taskDto) {

    }
}
