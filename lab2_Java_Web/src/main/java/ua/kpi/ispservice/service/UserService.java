package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void updateStatus(User user, boolean isBlocked) {
        userRepository.updateStatus(user, isBlocked);
    }
}
