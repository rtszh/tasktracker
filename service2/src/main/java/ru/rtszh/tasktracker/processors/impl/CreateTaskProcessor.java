package ru.rtszh.tasktracker.processors.impl;

import lombok.RequiredArgsConstructor;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.function.Consumer;

import static ru.rtszh.tasktracker.factories.TaskDtoFactory.createTaskDtoFromMessage;

@RequiredArgsConstructor
public class CreateTaskProcessor implements Consumer<Message> {

    private final TaskService taskService;

    @Override
    public void accept(Message message) {
        taskService.addTask(createTaskDtoFromMessage(message));
    }
}
