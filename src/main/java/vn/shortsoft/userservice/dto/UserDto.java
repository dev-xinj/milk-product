package vn.shortsoft.userservice.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.shortsoft.userservice.model.UserSession;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto implements Serializable{

    Long id;
    String userName;
    String password;
    String email;
    String phoneNumber;
    UserSession userSession;
    Set<UserRolesDto> userRolesDto;
}
