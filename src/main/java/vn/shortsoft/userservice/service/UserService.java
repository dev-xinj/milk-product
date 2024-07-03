package vn.shortsoft.userservice.service;

import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.request.ChangePasswordRequest;
import vn.shortsoft.userservice.response.DataResponse;

public interface UserService {

    DataResponse saveUser(UserDto user);

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    UserDto getUserByUserName(String userName);

    DataResponse login(UserDto userDto);

    DataResponse changePassword(ChangePasswordRequest changePasswordRequest);
}
