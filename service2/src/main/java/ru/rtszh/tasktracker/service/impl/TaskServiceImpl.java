package ru.rtszh.tasktracker.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtszh.tasktracker.domain.Task;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.dto.DtoToCreateTask;
import ru.rtszh.tasktracker.dto.DtoToDeleteTask;
import ru.rtszh.tasktracker.dto.TaskDto;
import ru.rtszh.tasktracker.dto.TaskToUpdateDto;
import ru.rtszh.tasktracker.exceptions.OrderNumberNullException;
import ru.rtszh.tasktracker.exceptions.UnknownChatException;
import ru.rtszh.tasktracker.exceptions.UnknownTaskException;
import ru.rtszh.tasktracker.factories.TaskDtoFactory;
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

@Service
@Slf4j
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
    public List<TaskDto> findAllTasksByUser(String chatId) {

        List<Task> userTasks = taskRepository.getTasksByChatId(chatId);

        return userTasks.stream()
                .map(TaskDtoFactory::createDtoToCreateTask)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void addTask(DtoToCreateTask taskDto) {

        Task task = Task.builder()
                .title(taskDto.title())
                .description(taskDto.description())
                .build();

        var savedTask = taskRepository.save(task);

        var userOptional = userRepository.findUserByChatId(taskDto.chatId());

        userOptional.ifPresentOrElse(
                addTaskToExistingUser(savedTask),
                addUserIfItNotExistsAndAddTask(taskDto.chatId(), savedTask)
        );

        log.info("Task added successfully: {}", savedTask);

    }

    @Override
    @Transactional
    public void updateTask(TaskToUpdateDto taskToUpdateDto) {

        checkOrderNumber(taskToUpdateDto.orderNumber());

        var userLogin = taskToUpdateDto.userLogin();

        List<Task> filteredTasks = taskRepository.getTasksByTitleAndUserLogin(userLogin, taskToUpdateDto.title());

        if (filteredTasks.size() == 0) {
//            addTask(createTaskDtoFromTaskToUpdateDto(taskToUpdateDto));
        } else {
            updateTask(taskToUpdateDto, filteredTasks);
        }

    }

    @Override
    @Transactional
    public void deleteTask(DtoToDeleteTask taskDto) {
        Optional<User> optionalUser = userRepository.findUserByChatId(taskDto.chatId());

        User foundedUser = optionalUser.orElseThrow(() -> new UnknownChatException(
                        String.format("User with chatId '%s' not found", taskDto.chatId())
                )
        );

        List<Task> tasks = foundedUser.getTasks();

        var taskToDelete = getTaskToDelete(tasks, taskDto.title(), taskDto.orderNumber());

        taskRepository.delete(taskToDelete);
    }

    private Task getTaskToDelete(List<Task> tasks, String taskTitleToDelete, Integer orderNumber) {
        return tasks.stream()
                .filter(task -> task.getTitle().equals(taskTitleToDelete))
                .filter(task -> task.getOrderNumber().equals(orderNumber))
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

    private Runnable addUserIfItNotExistsAndAddTask(String chatId, Task savedTask) {
        return () -> {
            savedTask.setOrderNumber(0);

            userRepository.save(
                    User.builder()
                            .chatId(chatId)
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
