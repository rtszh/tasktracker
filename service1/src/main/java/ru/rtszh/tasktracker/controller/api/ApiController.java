package ru.rtszh.tasktracker.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rtszh.tasktracker.dto.TaskToCreateDto;
import ru.rtszh.tasktracker.dto.TaskToDeleteDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;
import ru.rtszh.tasktracker.service.TaskService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final TaskService taskService;

    public ApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{userLogin}")
    public ResponseEntity<String> findAllTasksByUser(@PathVariable String userLogin) {

        var message = taskService.findAllUserTasks(userLogin);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/tasks/add")
    public ResponseEntity<String> addTask(@RequestBody TaskToCreateDto taskToCreateDto) {

        var message = taskService.createTask(taskToCreateDto);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/tasks/update")
    public ResponseEntity<String> updateTask(@RequestBody TaskToUpdateDto taskToUpdateDto) {

        var message = taskService.updateTask(taskToUpdateDto);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/tasks/delete")
    public ResponseEntity<Object> deleteTask(@RequestBody TaskToDeleteDto taskToDeleteDto) {

        var message = taskService.deleteTask(taskToDeleteDto);

        return ResponseEntity.ok(message);
    }

}
