package ru.rtszh.tasktracker.dto.messages.producing;

import lombok.Builder;
import org.springframework.lang.NonNull;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record TaskToDeleteMessage(
        @NonNull
        String userLogin,
        @NonNull
        String taskTitle,
        @NonNull
        ActionType actionType) {
}
