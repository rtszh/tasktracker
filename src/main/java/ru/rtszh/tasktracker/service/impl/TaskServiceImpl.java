package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.exceptions.TasksSizeException;
import ru.rtszh.tasktracker.exceptions.UncorrectedTaskSpecify;
import ru.rtszh.tasktracker.exceptions.UnknownUserException;
import ru.rtszh.tasktracker.repository.TaskRepository;
import ru.rtszh.tasktracker.repository.UserRepository;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // TODO: сделать нормальный input
    private final Scanner scanner = new Scanner(System.in);

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task addTask(TaskDto taskDto) {

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .build();

        var savedTask = taskRepository.save(task);

        var userOptional = userRepository.findUserByLogin(taskDto.getUserLogin());

        userOptional.ifPresentOrElse(
                addTaskToExistingUser(savedTask),
                addUserIfItNotExistsAndAddTask(taskDto.getUserLogin(), savedTask)
        );


        return savedTask;
    }

    @Override
    @Transactional
    public void deleteTask(TaskDto taskDto) {

        Optional<User> optionalUser = userRepository.findUserByLogin(taskDto.getUserLogin());

        User foundedUser = optionalUser.orElseThrow(() -> new UnknownUserException(
                        String.format("User '%s' not found", taskDto.getUserLogin())
                )
        );

        List<Task> tasks = foundedUser.getTasks();

        Task uniqueTask = checkUniqueTasks(foundedUser);

        taskRepository.delete(uniqueTask);
    }

    private Task checkUniqueTasks(User user) {

        int userTasksCount = user.getTasks().size();

        if (userTasksCount == 0) {
            throw new TasksSizeException(
                    String.format("User %s has no tasks", user.getLogin())
            );
        } else if (userTasksCount == 1) {
            return user.getTasks().get(0);
        } else {
            return specifyTaskToDelete(user);
        }
    }

    private Task specifyTaskToDelete(User user) {

        List<Task> tasks = taskRepository.getTasksByUserId(user.getId());

        System.out.println("Specify number of task which need to delete:");
        for (int i = 0; i < tasks.size(); i++) {

            String taskTitle = tasks.get(i).getTitle();
            String taskDescription = tasks.get(i).getDescription();

            // TODO: сделать нормальный output
            System.out.println(
                    String.format("Task %d: %s\n\t%s", i + 1, taskTitle, taskDescription)
            );
        }

        int taskNumberToDelete = scanner.nextInt();

        Optional<Task> specifiedTask = Optional.ofNullable(tasks.get(taskNumberToDelete - 1));

        return specifiedTask
                .orElseThrow(() -> new UncorrectedTaskSpecify(
                                String.format(
                                        "Task %d doesn't exist", taskNumberToDelete)
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
