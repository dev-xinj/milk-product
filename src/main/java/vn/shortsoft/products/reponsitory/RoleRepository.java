package vn.shortsoft.products.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.shortsoft.products.model.Roles;


@Repository
public interface RoleRepository extends CrudRepository<Roles, Long> {
    // Roles findFirstByName(String role);

}