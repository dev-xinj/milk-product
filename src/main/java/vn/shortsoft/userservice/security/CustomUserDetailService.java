package vn.shortsoft.userservice.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

        @Autowired
        UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // TODO Auto-generated method stub
                User user = userRepository.findByUserName(username)
                                .orElseThrow(() -> new UnsupportedOperationException(
                                                "Unimplemented method 'loadUserByUsername'"));
                List<GrantedAuthority> authorities = user.getUserRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                                .collect(Collectors.toList());

                return new CustomUserDetails(user, authorities);
        }

}
