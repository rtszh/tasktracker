package ru.rtszh.tasktracker.dto.messages.producing;

import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record TaskToUpdateMessage(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle,
        @Nullable
        String newTaskTitle,
        @Nullable
        String taskDescription,
        @NonNull
        Integer taskOrderNumber,
        @NonNull
        ActionType actionType) {
}
