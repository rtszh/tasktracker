package ru.rtszh.tasktracker.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.service.TaskService;
import ru.rtszh.tasktracker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final TaskService taskService;

    public ApiController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {

        var userList = userService.findAllUsers();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {

        var user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAllTasks() {

        var taskList = taskService.findAllTasks();

        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/tasks/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {

        taskService.addTask(taskDto);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/tasks/delete")
    public ResponseEntity<Object> deleteTask(@RequestBody TaskDto taskDto) {

        taskService.deleteTask(taskDto);

        return ResponseEntity.ok(null);
    }

}
