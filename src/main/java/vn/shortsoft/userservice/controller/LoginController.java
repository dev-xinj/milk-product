package vn.shortsoft.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.service.UserService;

@RestController
@RequestMapping("/v1/public")
public class LoginController {
@Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        Long id = userService.saveUser(user);
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.login(userDto));
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
