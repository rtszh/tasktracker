package ru.rtszh.tasktracker.dto.messages.producing;

import lombok.Builder;
import org.springframework.lang.NonNull;
import ru.rtszh.tasktracker.domain.ActionType;

@Builder
public record UserTasksMessage(
        @NonNull
        String userLogin,
        @NonNull
        ActionType actionType) {
}
