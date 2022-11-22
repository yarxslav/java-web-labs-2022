package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.dao.UserDaoImpl;

public class UserRepository {

    private UserDaoImpl userDao;

    public UserRepository(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public String defineUserRole(User user) {
        return userDao.role(user);
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void updateStatus(User user, boolean isBlocked) {
        userDao.updateStatus(user, isBlocked);
    }
}
