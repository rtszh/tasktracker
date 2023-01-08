package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(long id);
}
