package ru.rtszh.tasktracker.dto;


import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record DtoToDeleteTask(
        @NonNull
        String chatId,
        @NonNull
        String title,
        @NonNull
        Integer orderNumber
) {
}
