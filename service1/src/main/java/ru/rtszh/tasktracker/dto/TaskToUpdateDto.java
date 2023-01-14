package ru.rtszh.tasktracker.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record TaskToUpdateDto(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle,
        @Nullable
        String newTaskTitle,
        @Nullable
        String taskDescription,
        @NonNull
        Integer taskOrderNumber) {
}
