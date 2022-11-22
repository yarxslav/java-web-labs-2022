package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.Role;
import ua.kpi.ispservice.repository.dao.RoleDaoImpl;

public class RoleRepository {

    private RoleDaoImpl roleDao;

    public RoleRepository(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    public Role findByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }
}
