package vn.shortsoft.userservice.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.exception.ExistResourceException;
import vn.shortsoft.userservice.exception.IncorrectPasswordException;
import vn.shortsoft.userservice.exception.NotExistResourceException;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.response.DataResponse;
import vn.shortsoft.userservice.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
    private String secretKey = "24q5PhwxA02MndFFZ9HZmeBQ2w54wU7TvQgux189O0gjqizOeSbLBnGcFsXvPqxx";
    @Autowired
    private UserDao userDao;

    @Override
    public Long saveUser(User user) {
        Boolean isSave = false;
        isSave = checkEmailAndUserName(user.getEmail(), user.getUserName());
        if (isSave) {
            String cryp = passwordEncoder.encode(user.getPassword());
            user.setPassword(cryp);
        }
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        } else {
            return userDao.getUserByEmail(email);
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return null;
        } else {
            return userDao.getUserByUserName(userName);
        }
    }

    @Override
    public DataResponse verifyLoginByPassword(UserDto userDto) {
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
                    String token = generateToken(userDto);
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

    @Override
    public DataResponse verifyLoginByJwt(String jwt) {
        return DataResponse.builder().build();
    }

    private String generateToken(UserDto userDto) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .issueTime(new Date())
                .issuer("shortsoft.vn")
                .subject(null)
                .expirationTime(Date.from(expirationTime))
                .audience("123")

                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (KeyLengthException e) {
            log.info("Độ dài của Secret Key không đúng: ");
        } catch (JOSEException e) {
            log.info(e.getMessage());
        }
        return null;
    }

}
