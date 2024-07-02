package vn.shortsoft.userservice.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.model.UserRoles;
import vn.shortsoft.userservice.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("register")
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

    @GetMapping("profile")
    public ResponseEntity<?> getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return ResponseEntity.ok().body(userService.getUserByUserName(userName));
    }

}
