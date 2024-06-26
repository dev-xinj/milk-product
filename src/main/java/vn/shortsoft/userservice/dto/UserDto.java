package vn.shortsoft.userservice.dto;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.shortsoft.userservice.model.UserRoles;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String userName;
    String password;
    String email;
    String phoneNumber;
    Set<UserRoles> userRoles;
}
