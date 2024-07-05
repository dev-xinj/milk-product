package vn.shortsoft.userservice.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import vn.shortsoft.userservice.contants.ExpireContant;
import vn.shortsoft.userservice.convert.ConvertObjectToDto;
import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.exception.ExistResourceException;
import vn.shortsoft.userservice.exception.IncorrectPasswordException;
import vn.shortsoft.userservice.exception.NotExistResourceException;
import vn.shortsoft.userservice.exception.NotFoundResource;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.model.UserRoles;
import vn.shortsoft.userservice.model.UserSession;
import vn.shortsoft.userservice.repository.UserSessionRepository;
import vn.shortsoft.userservice.request.ChangePasswordRequest;
import vn.shortsoft.userservice.response.DataResponse;
import vn.shortsoft.userservice.response.RegisterResponse;
import vn.shortsoft.userservice.service.RolesService;
import vn.shortsoft.userservice.service.UserService;
import vn.shortsoft.userservice.utils.JwtUtil;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolesService rolesService;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Override
    public DataResponse saveUser(UserDto userDto) {
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
                return DataResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Cập nhật thành công")
                        .status(HttpStatus.OK.name())
                        .build();
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
                roles = userDto.getUserRolesDto().stream().map(role -> Roles.builder()
                        .name(role.getRoleDto().getName())
                        .code(role.getRoleDto().getCode()).build())
                        .collect(Collectors.toSet());
                roles.forEach(role -> rolesService.saveRole(role));
                // set User Session

                // Set UserRoles for user
                roles.stream().forEach(role -> user.addUserRoles(UserRoles.builder()
                        .role(role)
                        .build()));
                isSave = checkEmailAndUserName(user.getEmail(), user.getUserName());
                if (isSave) {
                    String cryp = passwordEncoder.encode(user.getPassword());
                    user.setPassword(cryp);
                    Long id = userDao.saveUser(user);
                    return DataResponse.builder()
                            .code(HttpStatus.CREATED.value())
                            .data(RegisterResponse.builder()
                                    .id(id)
                                    .build())
                            .message("Tạo user thành công")
                            .status(HttpStatus.CREATED.name())
                            .build();
                }
            }
        }
        throw new NotFoundResource("Không Tìm Thấy Dữ Liệu");

    }

    @Override
    public UserDto getUserById(Long id) {
        return ConvertObjectToDto.convertUserToUserDto(userDao.getUserById(id));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        if (StringUtils.hasLength(email)) {
            User u = userDao.getUserByEmail(email);
            return ConvertObjectToDto.convertUserToUserDto(u);
        } else {
            return null;
        }
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        if (StringUtils.hasLength(userName)) {
            return ConvertObjectToDto.convertUserToUserDto(userDao.getUserByUserName(userName));
        } else {
            return null;
        }
    }

    @Override
    public DataResponse login(UserDto userDto) {
        if (getUserByEmail(userDto.getEmail()) == null) {
            throw new NotExistResourceException("Email không tồn tại");
        } else {
            User user = userDao.getUserByUserName(userDto.getUserName());
            if (user == null) {
                throw new NotExistResourceException("User không tồn tại");
            } else {
                Boolean verifyPassword = passwordEncoder.matches(userDto.getPassword(), user.getPassword());

                if (verifyPassword) {
                    Map<String, String> map = new HashMap<>();
                    String accesstoken = JwtUtil.generateAccessToken(userDto);
                    String refreshToken = JwtUtil.generateRefreshToken(new HashMap<>(), userDto);
                    log.info("user name: " + JwtUtil.extractUserName(accesstoken));
                    map.put("Access token", accesstoken);

                    UserSession userSession = ConvertObjectToDto.convertUserSession(userDto.getUserSession());
                    userSession.setExpirationTime(new Timestamp(ExpireContant.EXPIRE_DATE.toEpochMilli()));
                    userSession.setIsExpired(false);
                    userSession.setIsRevoked(false);
                    userSession.setAccessToken(accesstoken);
                    userSession.setRefreshToken(refreshToken);
                    userSession.getAccessToken();
                    userSession.setUser(user);
                    userSessionRepository.save(userSession);
                    user.addUserSession(userSession);
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

    @Override
    public DataResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.getUserByUserName(authentication.getName());
        if (user != null && user.getId() > 0) {
            if (StringUtils.hasLength(changePasswordRequest.getCurrentPassword())
                    && StringUtils.hasLength(changePasswordRequest.getNewPassword())) {
                boolean isTruePassword = passwordEncoder.matches(changePasswordRequest.getCurrentPassword(),
                        user.getPassword());
                if (isTruePassword) {
                    user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                    userDao.saveUser(user);
                } else {
                    throw new IncorrectPasswordException("Mật khẩu không chính xác");
                }
            } else {
                throw new NotExistResourceException("Mật khẩu không tồn tại");
            }
        } else {
            throw new NotExistResourceException("User không tồn tại");
        }
        return DataResponse.builder()
                .code(HttpStatus.RESET_CONTENT.value())
                .status(HttpStatus.RESET_CONTENT.name())
                .message("Change password successfully")
                .build();
    }

}
