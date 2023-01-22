package ru.rtszh.tasktracker.domain;

import lombok.Getter;

public enum ActionTypesWithWaiting {
    CREATE_TASK("/create_task"),
    UPDATE_TASK("/update_task"),
    DELETE_TASK("/delete_task");
    @Getter
    private final String typeName;

    ActionTypesWithWaiting(String typeName) {
        this.typeName = typeName;
    }
}
