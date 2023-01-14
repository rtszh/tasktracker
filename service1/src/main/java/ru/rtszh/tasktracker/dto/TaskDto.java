package ru.rtszh.tasktracker.dto;

import org.springframework.lang.Nullable;

public record TaskDto(String userLogin, String taskTitle, @Nullable String taskDescription) {

}
