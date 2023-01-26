package ru.rtszh.tasktracker.processors.impl.actions;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.model.ActionTypesWithWaiting;
import ru.rtszh.tasktracker.dto.ConsumedMessage;
import ru.rtszh.tasktracker.exceptions.InvalidRegexException;
import ru.rtszh.tasktracker.factories.TaskDtoFactory;
import ru.rtszh.tasktracker.processors.ActionTypeProcessor;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import static ru.rtszh.tasktracker.factories.ProducedMessageFactory.createProducedMessageWithBody;

@Component("/delete_task")
public class DeleteTaskActionProcessor implements ActionTypeProcessor {

    private static final String DELETE_TASK_REGEX_1 = ".*[/]{2}[t]{1}.*[/]{2}[o]{1}.*";
    private static final String DELETE_TASK_REGEX_2 = ".*[/]{2}[t]{1}.*";
    private static final String DELETE_TASK_REGEX_3 = "[/]{2}[t]{1}";
    private static final String DELETE_TASK_REGEX_4 = "[/]{2}[o]{1}";

    private final MessageSender messageSender;
    private final TaskService taskService;

    private final ExecutorService executorService;

    public DeleteTaskActionProcessor(MessageSender messageSender, TaskService taskService, ExecutorService executorService) {
        this.messageSender = messageSender;
        this.taskService = taskService;
        this.executorService = executorService;
    }

    @Override
    public void process(ConsumedMessage message) {

        String messageText = message.message().getText();
        long chatId = message.message().getChatId();
        String title;
        Integer orderNumber;

        if (isTextMatchesToPattern(DELETE_TASK_REGEX_1, messageText)) {
            title = getTitle(messageText, DELETE_TASK_REGEX_3, DELETE_TASK_REGEX_4);
            orderNumber = getOrderNumber(messageText, DELETE_TASK_REGEX_4);
        } else if (isTextMatchesToPattern(DELETE_TASK_REGEX_2, messageText)) {
            title = getTitle(messageText, DELETE_TASK_REGEX_3);
            orderNumber = 1;
        } else {
            sendMessageThatMessageDoesntMatchPattern(chatId);
            return;
        }

        executorService.submit(
                () -> taskService.deleteTask(
                        TaskDtoFactory.createDtoToDeleteTask(chatId, title, orderNumber - 1)
                )
        );

    }


    private String getTitle(String messageText, String regexForTitleStartPosition, String regexForTitleEndPosition) {
        int start = getEndRegexPosition(regexForTitleStartPosition, messageText);

        int end = getStartRegexPosition(regexForTitleEndPosition, messageText);

        return messageText.substring(start, end).trim();
    }

    private String getTitle(String messageText, String regexForTitleStartPosition) {
        int start = getEndRegexPosition(regexForTitleStartPosition, messageText);

        return messageText.substring(start).trim();
    }

    private Integer getOrderNumber(String messageText, String regexForDescriptionStartPosition) {
        int start = getEndRegexPosition(regexForDescriptionStartPosition, messageText);

        return Integer.parseInt(messageText.substring(start).trim());
    }

    private int getStartRegexPosition(String regex, String input) {
        var pattern = Pattern.compile(regex);

        var matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.start();
        } else {
            throw new InvalidRegexException();
        }
    }

    private int getEndRegexPosition(String regex, String input) {
        var pattern = Pattern.compile(regex);

        var matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.end();
        } else {
            throw new InvalidRegexException();
        }
    }

    private boolean isTextMatchesToPattern(String regex, String text) {
        return Pattern.matches(regex, text);
    }

    private void sendMessageThatMessageDoesntMatchPattern(Long chatId) {
        messageSender.sendMessage(
                createProducedMessageWithBody(
                        chatId,
                        "Message does not match pattern for command: " + ActionTypesWithWaiting.DELETE_TASK
                )
        );
    }
}
