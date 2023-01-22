package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Builder
public record ProducedMessageWithTasks(
        @NonNull
        @JsonProperty("message")
        Message message,

        @NonNull
        @JsonProperty("tasks")
        List<TaskDto> taskDtoList) {
}

