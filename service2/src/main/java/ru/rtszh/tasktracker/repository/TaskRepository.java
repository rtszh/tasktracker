package ru.rtszh.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rtszh.tasktracker.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT id, title, description FROM task WHERE user_id = :userId", nativeQuery = true)
    List<Task> getTasksByUserId(long userId);
}
