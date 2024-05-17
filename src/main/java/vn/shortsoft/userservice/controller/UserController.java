package vn.shortsoft.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.userservice.model.User;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping("register")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        return ResponseEntity.ok().body(user);
    }
}
