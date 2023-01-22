package ru.rtszh.tasktracker.dto.messages.consuming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.lang.NonNull;
import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

@Builder
public record ReceivedMessage(
        @NonNull
        @JsonProperty("chatId")
        String chatId,

        @NonNull
        @JsonProperty("tasks")
        List<TaskDto> taskDtoList) {

}
