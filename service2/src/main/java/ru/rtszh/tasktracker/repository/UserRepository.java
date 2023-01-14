package ru.rtszh.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtszh.tasktracker.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
}
