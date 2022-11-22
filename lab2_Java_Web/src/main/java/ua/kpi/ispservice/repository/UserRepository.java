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

    public void blockUser(User user) {
        userDao.block(user);
    }

    public void unblockUser(User user) {
        userDao.unblock(user);
    }
}
