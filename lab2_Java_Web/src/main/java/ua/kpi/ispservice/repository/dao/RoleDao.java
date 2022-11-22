package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Role;

public interface RoleDao {

    Role getRoleByName(String name);
}
