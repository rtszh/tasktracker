package ru.rtszh.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rtszh.tasktracker.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT task.id, task.title, task.description, task.order_number " +
            "FROM task " +
            "LEFT JOIN users ON users.id = task.user_id  " +
            "WHERE users.chat_id = :chatId", nativeQuery = true)
    List<Task> getTasksByChatId(String chatId);

    @Query(value = "SELECT task.id, task.title, task.description, task.order_number " +
            "FROM task " +
            "LEFT JOIN users ON users.id = task.user_id  " +
            "WHERE users.id = :userId AND task.title = :taskTitle", nativeQuery = true)
    List<Task> getTasksByTitleAndUserId(Long userId, String taskTitle);
}
