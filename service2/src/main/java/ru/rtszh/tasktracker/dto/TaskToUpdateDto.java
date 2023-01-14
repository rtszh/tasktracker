package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record TaskToUpdateDto(
        String title,
        @Nullable
        String updatedTitle,
        @Nullable
        String description,
        String userLogin,
        Integer orderNumber) {
}
