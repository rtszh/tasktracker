package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.Message;

@Builder
public record ConsumedMessage(
        @NonNull
        @JsonProperty("update_id")
        long update_id,

        @NonNull
        @JsonProperty("message")
        Message message
) {
}

