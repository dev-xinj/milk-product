package vn.shortsoft.userservice.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.repository.UserRepository;

@Log4j2
@Component
public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;

        // public CustomUserDetailsService(UserRepository repository) {
        //         this.userRepository = repository;
        // }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // TODO Auto-generated method stub
                log.info("vào đây");
                User user = userRepository.findByUserName(username)
                                .orElseThrow(() -> new UnsupportedOperationException(
                                                "Unimplemented method 'loadUserByUsername'"));
                List<GrantedAuthority> authorities = user.getUserRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                                .collect(Collectors.toList());

                return new CustomUserDetails(user, authorities);
        }

}
