package ua.kpi.ispservice.service;

import ua.kpi.ispservice.ApplicationContext;
import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.UserRepository;

public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            ApplicationContext.getInstance().setCurrentUser(user);
            return true;
        }

        return false;
    }

    public String defineUserRole(User user) {
        if (user != null) {
            return userRepository.defineUserRole(user);
        } else {
            return "NO_ROLE";
        }
    }
}
