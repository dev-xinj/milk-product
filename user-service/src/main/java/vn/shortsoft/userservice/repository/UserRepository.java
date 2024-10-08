package vn.shortsoft.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = { "userRoles" })
    Optional<User> findByUserName(String userName);

}
