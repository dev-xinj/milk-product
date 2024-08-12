package vn.shortsoft.userservice.dao.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.shortsoft.userservice.dao.RolesDao;
import vn.shortsoft.userservice.exception.NotExistResourceException;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.repository.RolesRepository;

@Component
public class RolesDaoImpl implements RolesDao {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Long saveRole(Roles role) {
        return rolesRepository.save(role).getId();
    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new NotExistResourceException("Not Found Roles By Id " + id));
    }

    @Override
    public Set<Roles> getRoleByName(String name) {
        Optional<Set<Roles>> optRoles = rolesRepository.findByName(name);

        if (optRoles.isPresent()) {
            return optRoles.get();
        }
        return null;
    }

}
