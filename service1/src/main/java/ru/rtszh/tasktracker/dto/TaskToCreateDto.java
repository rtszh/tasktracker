package ru.rtszh.tasktracker.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record TaskToCreateDto(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle,
        @Nullable
        String taskDescription,

        int orderNumber) {
}
