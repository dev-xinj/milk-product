package vn.shortsoft.userservice.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.shortsoft.userservice.dao.RolesDao;
import vn.shortsoft.userservice.exception.NotExistResourceException;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesDao rolesDao;

    @Override
    public Long saveRole(Roles role) {
        if (role != null) {
            if (role.getId() != null) {
                Roles newRoles = getRoleById(role.getId());
                if (StringUtils.hasLength(role.getName())) {
                    newRoles.setName(role.getName());
                }
                if (StringUtils.hasLength(role.getCode())) {
                    newRoles.setCode(role.getCode());
                }
                return rolesDao.saveRole(role);
            } else {
                return rolesDao.saveRole(role);
            }
        } else {
            throw new NotExistResourceException("Không Tồn Tại Roles");

        }

    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesDao.getRoleById(id);
    }

    @Override
    public Set<Roles> getRoleByName(String name) {
        if (StringUtils.hasLength(name)) {
            return rolesDao.getRoleByName(name);
        }
        return null;
    }

}
