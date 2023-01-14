package ru.rtszh.tasktracker.dto.messages.producing;

import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record TaskToCreateMessage(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle,
        @Nullable
        String taskDescription,
        @NonNull
        ActionType actionType) {
}
