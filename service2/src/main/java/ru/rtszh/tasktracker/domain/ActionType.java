package ru.rtszh.tasktracker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ActionType {
    @JsonProperty("create-task")
    CREATE_TASK,
    @JsonProperty("update-task")
    UPDATE_TASK,
    @JsonProperty("delete-task")
    DELETE_TASK,
    @JsonProperty("get-user-tasks")
    GET_USER_TASKS
}
