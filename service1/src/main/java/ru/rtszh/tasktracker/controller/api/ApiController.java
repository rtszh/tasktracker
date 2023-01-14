package ru.rtszh.tasktracker.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rtszh.tasktracker.dto.Message;
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

    @GetMapping ("/tasks/{userLogin}")
    public ResponseEntity<String> findAllTasksByUser(@PathVariable String userLogin) {

        var message = taskService.findAllUserTasks(userLogin);

        return ResponseEntity.ok(message);
    }

    @PostMapping ("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {

        var message = taskService.createTask(taskDto);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/tasks/delete")
    public ResponseEntity<Object> deleteTask(@RequestBody TaskDto taskDto) {

        var message = taskService.deleteTask(taskDto);

        return ResponseEntity.ok(message);
    }

}
