package vn.shortsoft.userservice.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> register(@RequestBody User user) {
        Long id = userService.saveUser(user);
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        try {
            // Xác thực người dùng
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDto.getUserName(), userDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lưu thông tin người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("user", authentication.getPrincipal());

            // Trả về thông tin người dùng
            return ResponseEntity.ok(authentication.getPrincipal());
        } catch (AuthenticationException e) {
            // Xử lý lỗi xác thực
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // return ResponseEntity.ok().body(userService.verifyLoginByPassword(userDto));

    }

    @PostMapping("/hello")
    public ResponseEntity<?> getMethodName() {
        Set<UserRoles> ur = new HashSet<>();
        ur.add(new UserRoles(new User(),new Roles()));
        return ResponseEntity.ok().body(new User().builder()
        .userRoles(ur)
        .build());
    }

}
