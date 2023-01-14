package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.domain.User;
import ru.rtszh.tasktracker.repository.UserRepository;
import ru.rtszh.tasktracker.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {

        var optionalUser = userRepository.findById(id);

        return optionalUser.orElseThrow(() -> new RuntimeException("Unknown user"));
    }
}
