package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.exceptions.UnknownTaskException;
import ru.rtszh.tasktracker.exceptions.UnknownUserException;
import ru.rtszh.tasktracker.factories.TaskDtoFactory;
import ru.rtszh.tasktracker.repository.TaskRepository;
import ru.rtszh.tasktracker.repository.UserRepository;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static ru.rtszh.tasktracker.factories.TaskDtoFactory.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskDto> findAllTasksByUser(String userLogin) {

        List<Task> userTasks = taskRepository.getTasksByUserLogin(userLogin);

        return userTasks.stream()
                .map(task -> createTaskDtoFromTaskAndUserLogin(task, userLogin))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Task addTask(TaskDto taskDto) {

        Task task = Task.builder()
                .title(taskDto.title())
                .description(taskDto.description())
                .build();

        var savedTask = taskRepository.save(task);

        var userOptional = userRepository.findUserByLogin(taskDto.userLogin());

        userOptional.ifPresentOrElse(
                addTaskToExistingUser(savedTask),
                addUserIfItNotExistsAndAddTask(taskDto.userLogin(), savedTask)
        );

        return savedTask;
    }

    @Override
    @Transactional
    public void deleteTask(TaskDto taskDto) {

        Optional<User> optionalUser = userRepository.findUserByLogin(taskDto.userLogin());

        User foundedUser = optionalUser.orElseThrow(() -> new UnknownUserException(
                        String.format("User '%s' not found", taskDto.userLogin())
                )
        );

        List<Task> tasks = foundedUser.getTasks();

        var taskToDelete = getTaskToDelete(tasks, taskDto.title());

        taskRepository.delete(taskToDelete);
    }

    private Task getTaskToDelete(List<Task> tasks, String taskTitleToDelete) {
        return tasks.stream()
                .filter(task -> task.getTitle().equals(taskTitleToDelete))
                .findFirst()
                .orElseThrow(() -> new UnknownTaskException(
                                String.format("Task %s doesn't exist", taskTitleToDelete)
                        )
                );
    }

    private Consumer<User> addTaskToExistingUser(Task savedTask) {
        return user ->
                user.getTasks().add(savedTask);
    }

    private Runnable addUserIfItNotExistsAndAddTask(String userLogin, Task savedTask) {
        return () -> userRepository.save(
                User.builder()
                        .login(userLogin)
                        .tasks(
                                List.of(savedTask)
                        )
                        .build()
        );
    }
}
