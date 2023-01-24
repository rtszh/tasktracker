package ru.rtszh.tasktracker.model;

import lombok.Getter;

public enum ActionTypesWithoutWaiting {
    GET_USER_TASKS("/get_tasks"),
    NO_ENTERED_COMMAND("/no_entered_command");

    @Getter
    private final String typeName;

    ActionTypesWithoutWaiting(String typeName) {
        this.typeName = typeName;
    }
}
