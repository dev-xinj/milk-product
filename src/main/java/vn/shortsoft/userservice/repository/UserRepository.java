package vn.shortsoft.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.userservice.model.User;


public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);

}
