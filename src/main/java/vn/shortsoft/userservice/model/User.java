package vn.shortsoft.userservice.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@Component
@Entity
@Table(name = "user")
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
}
