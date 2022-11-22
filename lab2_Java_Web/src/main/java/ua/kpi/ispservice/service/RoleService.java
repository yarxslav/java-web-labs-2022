package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.Role;
import ua.kpi.ispservice.repository.RoleRepository;

public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
