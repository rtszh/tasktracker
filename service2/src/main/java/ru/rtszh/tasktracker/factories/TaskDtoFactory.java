package ru.rtszh.tasktracker.factories;

import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.DtoToCreateTask;
import ru.rtszh.tasktracker.dto.DtoToDeleteTask;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;

public class TaskDtoFactory {

    public static TaskDto createDtoToCreateTask(Task task) {
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .orderNumber(task.getOrderNumber())
                .build();
    }


    public static TaskDto createTaskDtoFromTaskToUpdateDto(TaskToUpdateDto taskToUpdateDto) {
        return TaskDto.builder()
                .title(taskToUpdateDto.title())
                .description(taskToUpdateDto.description())
//                .userLogin(taskToUpdateDto.userLogin())
                .build();
    }

    public static DtoToCreateTask createDtoToCreateTask(long chatId, String title, String description) {
        return DtoToCreateTask.builder()
                .chatId(String.valueOf(chatId))
                .title(title)
                .description(description)
                .build();
    }

    public static DtoToDeleteTask createDtoToDeleteTask(long chatId, String title, Integer orderNumber) {
        return DtoToDeleteTask.builder()
                .chatId(String.valueOf(chatId))
                .title(title)
                .orderNumber(orderNumber)
                .build();
    }
}
