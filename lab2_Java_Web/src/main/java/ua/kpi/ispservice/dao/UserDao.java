package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.User;

import java.util.List;

public interface UserDao {

    void create(User user);
    void block(User user);
    void unblock(User user);
    String role(User user);
    List<User> findAll();
    User findUserByUsername(String username);

}
