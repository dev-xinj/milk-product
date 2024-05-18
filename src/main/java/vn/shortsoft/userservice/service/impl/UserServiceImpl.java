package vn.shortsoft.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public Long saveUser(User user) {
        String cryp = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryp);
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

}
