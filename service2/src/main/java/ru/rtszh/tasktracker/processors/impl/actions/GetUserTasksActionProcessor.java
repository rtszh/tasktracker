package ru.rtszh.tasktracker.processors.impl.actions;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.dto.ConsumedMessage;
import ru.rtszh.tasktracker.processors.ActionTypeProcessor;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.rtszh.tasktracker.factories.ProducedMessageFactory.createProducedMessageWithChatId;

@Component("/get_tasks")
public class GetUserTasksActionProcessor implements ActionTypeProcessor {

    private final MessageSender messageSender;
    private final ExecutorService executorService;
    private final TaskService taskService;

    public GetUserTasksActionProcessor(MessageSender messageSender, ExecutorService executorService, TaskService taskService) {
        this.messageSender = messageSender;
        this.executorService = executorService;
        this.taskService = taskService;
    }

    @Override
    public void process(ConsumedMessage message) {
        long chatId = message.message().getChatId();

        executorService.submit(
                getTaskListAndSend(chatId)
        );
    }

    private Runnable getTaskListAndSend(long chatId) {
        return () -> {
            var taskList = taskService.findAllTasksByUser(String.valueOf(chatId));
            messageSender.sendMessage(
                    createProducedMessageWithChatId(chatId, taskList)
            );
        };
    }
}
