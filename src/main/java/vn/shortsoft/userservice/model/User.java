package vn.shortsoft.userservice.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.userservice.listener.UserListener;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@EntityListeners(UserListener.class)
@Entity
@Table(name = "s_user")
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "email")
    private String email;
    @Column(name = "hash_password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles;

    public void addUserRoles(UserRoles userRole) {
        if (userRole != null) {
            if (userRoles == null) {
                userRoles = new HashSet<>();
            }
            userRoles.add(userRole);
            userRole.setUser(this);
        }
    }
}
