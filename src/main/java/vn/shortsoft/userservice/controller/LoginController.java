package vn.shortsoft.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.dto.UserSessionDto;
import vn.shortsoft.userservice.model.UserSession;
import vn.shortsoft.userservice.service.UserService;

@RestController
@RequestMapping("/v1/public")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        user.getUserSession().builder().sessionId(httpSession.getId()).build();
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        try {
            HttpSession httpSession = request.getSession();
            userDto.setUserSession(UserSessionDto.builder().sessionId(httpSession.getId()).build());
            return ResponseEntity.ok(userService.login(userDto));
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok().body("hell");
    }

}
