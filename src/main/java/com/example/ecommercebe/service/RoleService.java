package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Role;
import com.example.ecommercebe.statics.enums.ERole;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(long id);
    Optional<Role> getRoleByName(ERole name);
    ERole addRole(ERole role);
    Role updateRole(long id, Role role);
    void deleteRole(long id);
}
