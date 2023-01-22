package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record DtoToCreateTask(
        String chatId,
        String title,
        @Nullable
        String description) {
}
