package vn.shortsoft.userservice.service;

import vn.shortsoft.userservice.model.User;

public interface UserService {

    Long saveUser(User user);

    User getUserById(Long id);
}
