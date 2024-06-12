package vn.shortsoft.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.response.DataResponse;
import vn.shortsoft.userservice.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Long id = userService.saveUser(user);
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("login")
    public ResponseEntity<DataResponse> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.verifyLoginByPassword(userDto));

    }
}
