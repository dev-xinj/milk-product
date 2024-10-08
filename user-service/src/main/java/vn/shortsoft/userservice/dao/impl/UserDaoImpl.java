package vn.shortsoft.userservice.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.shortsoft.userservice.dao.UserDao;
import vn.shortsoft.userservice.exception.NotFoundResource;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;



    //Create and Update
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

    //Get User by Id
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundResource("Not Found User By Id: " + id ));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        return optUser.isPresent() ? optUser.get() : null;
    }

    @Override
    public User getUserByUserName(String userName) {
        Optional<User> optUser = userRepository.findByUserName(userName);
        return optUser.isPresent() ? optUser.get() : null;
    }
}
