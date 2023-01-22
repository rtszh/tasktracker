package ru.rtszh.tasktracker.processors.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.processors.TextFormatProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TextFormatProcessorImpl implements TextFormatProcessor {
    @Override
    public String getTextToSend(List<TaskDto> taskDtoList) {
        return taskDtoList.stream()
                .map(TaskDto::toString)
                .collect(Collectors.joining(",\n"));
    }
}
