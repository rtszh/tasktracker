package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record Message(String userLogin, String taskTitle, @Nullable String taskDescription, ActionType actionType) {

}
