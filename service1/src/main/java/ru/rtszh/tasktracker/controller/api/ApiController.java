package ru.rtszh.tasktracker.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final TaskService taskService;

    public ApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping ("/tasks")
    public ResponseEntity<List<Task>> findAllTasks() {

        var taskList = taskService.findAllTasks();

        return ResponseEntity.ok(taskList);
    }

    @PostMapping ("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody Task task) {

        var message = taskService.addTask(task);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/tasks/delete")
    public ResponseEntity<Object> deleteTask(@RequestBody TaskDto taskDto) {

        taskService.deleteTask(taskDto);

        return ResponseEntity.ok(null);
    }

}
