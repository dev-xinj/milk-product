package vn.shortsoft.userservice.dao;

import java.util.Set;

import vn.shortsoft.userservice.model.Roles;

public interface RolesDao {
    Long saveRole(Roles role);

    Roles getRoleById(Long id);

    Set<Roles> getRoleByName(String name);
}
