package vn.shortsoft.userservice.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.exception.ExistResourceException;
import vn.shortsoft.userservice.exception.IncorrectPasswordException;
import vn.shortsoft.userservice.exception.NotExistResourceException;
import vn.shortsoft.userservice.exception.NotFoundResource;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.repository.RolesRepository;
import vn.shortsoft.userservice.response.DataResponse;
import vn.shortsoft.userservice.service.RolesService;
import vn.shortsoft.userservice.service.UserService;
import vn.shortsoft.userservice.utils.JwtUtil;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    
    @Autowired
    private UserDao userDao;

    @Autowired
    private RolesService rolesService;

    @Override
    public Long saveUser(UserDto userDto) {
        User user;
        if (userDto != null) {
            if (userDto.getId() != null) { // Có ID thì vào cập nhật dữ liệu
                user = userDao.getUserById(userDto.getId());
                if (StringUtils.hasLength(userDto.getEmail())) { // Update Email
                    user.builder().email(userDto.getEmail()).build();
                }
                if (StringUtils.hasLength(userDto.getPhoneNumber())) { // Update SĐT
                    user.builder().phoneNumber(userDto.getPhoneNumber()).build();
                }
                if (StringUtils.hasLength(userDto.getUserName())) { // Update User Name
                    user.builder().userName(userDto.getUserName()).build();
                }
                if (StringUtils.hasLength(userDto.getPassword())) { // Update Password
                    boolean isTruePassword = passwordEncoder.matches(userDto.getPassword(), user.getPassword());
                    if (isTruePassword) {
                        user.builder().password(passwordEncoder.encode(userDto.getPassword())).build();
                    } else {
                        throw new IncorrectPasswordException("Password Incorrect");
                    }
                }
                return userDao.saveUser(user);
            } else { // Ngược lại. Tạo mới dữ liệu
                user = User.builder()
                        .userName(userDto.getUserName())
                        .password(userDto.getPassword())
                        .email(userDto.getEmail())
                        .phoneNumber(userDto.getPhoneNumber())
                        .build();

                Boolean isSave = false;
                Set<Roles> roles;
                // line is save Roles
                roles = userDto.getUserRoles().stream().map(role -> role.getRole()).collect(Collectors.toSet());
                roles.forEach(role -> rolesService.saveRole(role));
                // Set UserRoles for user
                userDto.getUserRoles().stream().forEach(userRole -> user.addUserRoles(userRole));
                isSave = checkEmailAndUserName(user.getEmail(), user.getUserName());
                if (isSave) {
                    String cryp = passwordEncoder.encode(user.getPassword());
                    user.setPassword(cryp);
                    return userDao.saveUser(user);
                }
            }
        }
        throw new NotFoundResource("Không Tìm Thấy Dữ Liệu");

    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        if (StringUtils.hasLength(email)) {
            return userDao.getUserByEmail(email);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        if (StringUtils.hasLength(userName)) {
            return userDao.getUserByUserName(userName);
        } else {
            return null;
        }
    }

    @Override
    public DataResponse login(UserDto userDto) {
        if (getUserByEmail(userDto.getEmail()) == null) {
            throw new NotExistResourceException("Email không tồn tại");
        } else {
            User user = getUserByUserName(userDto.getUserName());
            if (user == null) {
                throw new NotExistResourceException("Email không tồn tại");
            } else {
                Boolean verifyPassword = passwordEncoder.matches(userDto.getPassword(), user.getPassword());

                if (verifyPassword) {
                    Map<String, String> map = new HashMap<>();
                    String token = JwtUtil.generateAccessToken(userDto);
                    map.put("token", token);
                    return DataResponse.builder()
                            .code(200)
                            .status("OK")
                            .message("Đăng nhập thành công")
                            .data(map)
                            .build();
                } else {
                    throw new IncorrectPasswordException("Incorrect Password");
                }

            }
        }

    }

    private Boolean checkEmailAndUserName(String email, String userName) {
        if (getUserByEmail(email) != null) {
            throw new ExistResourceException("Email đã tồn tại");
        } else if (getUserByUserName(userName) != null) {
            throw new ExistResourceException("User đã tồn tại");
        } else {
            return true;
        }
    }


}
