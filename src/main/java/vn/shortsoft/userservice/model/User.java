package vn.shortsoft.userservice.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
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

    @OneToMany(mappedBy = "user")
    private Set<UserRoles> userRoles;

}
