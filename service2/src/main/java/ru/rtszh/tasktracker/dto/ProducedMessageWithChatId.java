package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.util.List;

@Builder
public record ProducedMessageWithChatId(
        @NonNull
        @JsonProperty("chatId")
        String chatId,

        @NonNull
        @JsonProperty("tasks")
        List<TaskDto> taskDtoList) {
}

