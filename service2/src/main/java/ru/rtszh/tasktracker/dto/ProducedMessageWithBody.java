package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record ProducedMessageWithBody(
        @NonNull
        @JsonProperty("chatId")
        String chatId,

        @NonNull
        @JsonProperty("tasks")
        String body
) {
}

