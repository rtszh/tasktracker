package ru.rtszh.tasktracker.domain;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record Task(String userLogin, String taskTitle, @Nullable String taskDescription, ActionType actionType) {

}
