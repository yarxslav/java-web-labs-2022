package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.User;

import java.util.List;

public interface UserDao {

    void create(User user);
    void updateStatus(User user, boolean isBlocked);
    String role(User user);
    List<User> findAll();
    User findUserByUsername(String username);

}
