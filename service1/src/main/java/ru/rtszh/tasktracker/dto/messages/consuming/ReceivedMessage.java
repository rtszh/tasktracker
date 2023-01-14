package ru.rtszh.tasktracker.dto.messages.consuming;

import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record ReceivedMessage(
        @NonNull
        String userLogin,
        @Nullable
        String taskTitle,
        @Nullable
        String taskDescription,
        @Nullable
        String newTaskTitle,
        @NonNull
        ActionType actionType) {

}
