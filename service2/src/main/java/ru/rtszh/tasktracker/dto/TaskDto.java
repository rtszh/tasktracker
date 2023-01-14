package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record TaskDto(
        String title,
        @Nullable
        String description,
        String userLogin) {

}
