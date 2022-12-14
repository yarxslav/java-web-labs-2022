package ua.kpi.ispservice.entity;

import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private boolean blocked;

    public User(String username, String password, Long roleId, boolean blocked) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.blocked = blocked;
    }

    public User(Long id, String username, String password, Long roleId, boolean blocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.blocked = blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
