package ru.rtszh.tasktracker.processors.impl;

import lombok.RequiredArgsConstructor;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.function.Consumer;

import static ru.rtszh.tasktracker.domain.ActionType.GET_USER_TASKS;
import static ru.rtszh.tasktracker.factories.MessageFactory.createMessageFromTaskDto;

@RequiredArgsConstructor
public class GetTasksByUserProcessor implements Consumer<Message> {

    private final TaskService taskService;
    private final MessageSender messageSender;


    @Override
    public void accept(Message message) {
        var tasks = taskService.findAllTasksByUser(message.userLogin());

        var messages = tasks.stream()
                .map(taskDto -> createMessageFromTaskDto(taskDto, GET_USER_TASKS))
                .toList();

        messages.forEach(messageSender::sendMessage);
    }
}
