package vn.shortsoft.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import vn.shortsoft.userservice.request.ChangePasswordRequest;
import vn.shortsoft.userservice.response.DataResponse;
import vn.shortsoft.userservice.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("profile")
    public ResponseEntity<?> getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return ResponseEntity.ok().body(userService.getUserByUserName(userName));
    }

    @PostMapping("change-password")
    public ResponseEntity<DataResponse> changePassword(@RequestBody ChangePasswordRequest passwordRequest){
        return ResponseEntity.ok().body(userService.changePassword(passwordRequest));
    }

}
