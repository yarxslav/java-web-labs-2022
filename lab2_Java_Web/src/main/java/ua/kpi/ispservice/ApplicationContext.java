package ua.kpi.ispservice;

import ua.kpi.ispservice.entity.User;

public class ApplicationContext {

    private static ApplicationContext instance;
    private User currentUser;

    private ApplicationContext(User user) {
        this.currentUser = user;
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext(null);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }


}
