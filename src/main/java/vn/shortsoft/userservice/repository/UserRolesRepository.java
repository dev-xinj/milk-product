package vn.shortsoft.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.userservice.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long>{
    
}
