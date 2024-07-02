package vn.shortsoft.userservice.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.shortsoft.userservice.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    
    Optional<Set<Roles>> findByName(String name);

}
