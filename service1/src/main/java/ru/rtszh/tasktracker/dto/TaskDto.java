package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

@Value
@Builder
public class TaskDto {
    String title;
    @Nullable
    String description;
    String userLogin;
}
