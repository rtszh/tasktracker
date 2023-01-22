package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.dto.ProducedMessageWithBody;
import ru.rtszh.tasktracker.dto.ProducedMessageWithChatId;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public class ProducedMessageFactory {
    public static ProducedMessageWithBody createProducedMessageWithBody(long chatId, String messageBody) {
        return ProducedMessageWithBody.builder()
                .chatId(String.valueOf(chatId))
                .body(messageBody)
                .build();
    }

    public static ProducedMessageWithChatId createProducedMessageWithChatId(long chatId, List<TaskDto> taskDtoList) {
        return ProducedMessageWithChatId.builder()
                .chatId(String.valueOf(chatId))
                .taskDtoList(taskDtoList)
                .build();
    }
}
