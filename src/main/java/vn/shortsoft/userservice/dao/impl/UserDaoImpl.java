package vn.shortsoft.userservice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long saveUser(User user) {
        if (user.getId() == null) {
            userRepository.save(user);
        }else{
            User u = getUserById(user.getId());
            if(user.getFirstName()!=null){
                u.setFirstName(user.getFirstName());
            }
        }
        return user.getId();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException());
    }
}
