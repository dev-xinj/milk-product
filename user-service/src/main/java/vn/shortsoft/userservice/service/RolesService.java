package vn.shortsoft.userservice.service;

import java.util.Set;

import vn.shortsoft.userservice.model.Roles;

public interface RolesService {
    Long saveRole(Roles role);

    Roles getRoleById(Long id);

    Set<Roles> getRoleByName(String name);
}
