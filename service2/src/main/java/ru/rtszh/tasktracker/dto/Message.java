package ru.rtszh.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Value
@Builder
public class Message {
    @JsonProperty("taskTitle")
    String title;
    @Nullable
    @JsonProperty("taskDescription")
    String description;
    @JsonProperty("userLogin")
    String userLogin;
    @JsonProperty("actionType")
    ActionType actionType;
}
