package vn.shortsoft.userservice.service;

import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.response.DataResponse;

public interface UserService {

    Long saveUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByUserName(String userName);

    DataResponse verifyLoginByPassword(UserDto userDto);

    DataResponse verifyLoginByJwt(String jwt);
}
