package ru.rtszh.tasktracker.dto;

import org.springframework.lang.NonNull;

public record TaskToDeleteDto(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle) {
}
