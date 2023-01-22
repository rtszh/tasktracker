package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Builder
public record TaskDto(
        @NonNull
        String title,
        @Nullable
        String description,
        @NonNull
        Integer orderNumber
) {
}
