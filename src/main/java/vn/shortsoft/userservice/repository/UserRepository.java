package vn.shortsoft.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.userservice.model.User;
public interface UserRepository extends JpaRepository<User,Long>{

}
