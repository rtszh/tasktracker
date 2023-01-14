package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;
import ru.rtszh.tasktracker.exceptions.OrderNumberNullException;
import ru.rtszh.tasktracker.exceptions.UnknownTaskException;
import ru.rtszh.tasktracker.exceptions.UnknownUserException;
import ru.rtszh.tasktracker.repository.TaskRepository;
import ru.rtszh.tasktracker.repository.UserRepository;
import ru.rtszh.tasktracker.service.TaskService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.rtszh.tasktracker.factories.TaskDtoFactory.createTaskDtoFromTaskAndUserLogin;
import static ru.rtszh.tasktracker.factories.TaskDtoFactory.createTaskDtoFromTaskToUpdateDto;

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
    public void addTask(TaskDto taskDto) {

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

    }

    @Override
    @Transactional
    public void updateTask(TaskToUpdateDto taskToUpdateDto) {

        checkOrderNumber(taskToUpdateDto.orderNumber());

        var userLogin = taskToUpdateDto.userLogin();

        List<Task> filteredTasks = taskRepository.getTasksByTitleAndUserLogin(userLogin, taskToUpdateDto.title());

        if (filteredTasks.size() == 0) {
            addTask(createTaskDtoFromTaskToUpdateDto(taskToUpdateDto));
        } else {
            updateTask(taskToUpdateDto, filteredTasks);
        }

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
        return user -> {

            List<Task> tasksWithSameTitle = taskRepository.getTasksByTitleAndUserId(user.getId(), savedTask.getTitle());

            int orderNumber = getOrderNumberFromTasksWithSameTitle(tasksWithSameTitle);

            savedTask.setOrderNumber(orderNumber);

            user.getTasks().add(savedTask);
        };
    }

    private int getOrderNumberFromTasksWithSameTitle(List<Task> tasks) {
        if (tasks.size() == 0) {
            return 0;
        } else {
            int maxOrderNumber = tasks.stream()
                    .map(Task::getOrderNumber)
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt();

            return ++maxOrderNumber;
        }

    }

    private Runnable addUserIfItNotExistsAndAddTask(String userLogin, Task savedTask) {
        return () -> {
            savedTask.setOrderNumber(0);

            userRepository.save(
                    User.builder()
                            .login(userLogin)
                            .tasks(
                                    List.of(savedTask)
                            )
                            .build()
            );
        };
    }

    private void updateTask(TaskToUpdateDto taskDto, List<Task> filteredTasks) {

        var sortedFilteredTasks = filteredTasks.stream()
                .sorted(Comparator.comparing(Task::getOrderNumber))
                .toList();

        var taskToUpdate = sortedFilteredTasks.get(taskDto.orderNumber());

        updateTitleForTask(taskToUpdate, taskDto.updatedTitle());
        updateDescriptionForTask(taskToUpdate, taskDto.description());

    }

    private void updateTitleForTask(Task task, String updatedTitle) {
        if (nonNull(updatedTitle)) {
            task.setTitle(updatedTitle);
        }
    }

    private void updateDescriptionForTask(Task task, String updatedDescription) {
        if (nonNull(updatedDescription)) {
            task.setDescription(updatedDescription);
        }
    }

    private void checkOrderNumber(Integer orderNumber) {
        if (isNull(orderNumber)) {
            throw new OrderNumberNullException("Order number for task must not be null");
        }
    }
}
