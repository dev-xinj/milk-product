package vn.shortsoft.userservice.dao;

import vn.shortsoft.userservice.model.User;

public interface UserDao {
    Long saveUser(User user);

    User getUserById(Long id);
}
