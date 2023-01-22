package ru.rtszh.tasktracker.processors;

import ru.rtszh.tasktracker.dto.TaskDto;

import java.util.List;

public interface TextFormatProcessor {
    String getTextToSend(List<TaskDto> taskDtoList);
}
