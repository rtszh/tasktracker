package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record Message(
        @Nullable
        @JsonProperty("taskTitle")
        String title,
        @Nullable
        @JsonProperty("taskDescription")
        String description,
        @JsonProperty("userLogin")
        String userLogin,
        @JsonProperty("actionType")
        ActionType actionType) {
}
