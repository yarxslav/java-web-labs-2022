package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void block(User user) {
        userRepository.blockUser(user);
    }

    public void unblock(User user) {
        userRepository.unblockUser(user);
    }
}
