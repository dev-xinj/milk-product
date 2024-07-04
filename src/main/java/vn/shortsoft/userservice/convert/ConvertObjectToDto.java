package vn.shortsoft.userservice.convert;

import java.util.stream.Collectors;

import vn.shortsoft.userservice.dto.RoleDto;
import vn.shortsoft.userservice.dto.UserDto;
import vn.shortsoft.userservice.dto.UserRolesDto;
import vn.shortsoft.userservice.model.Roles;
import vn.shortsoft.userservice.model.User;
import vn.shortsoft.userservice.model.UserRoles;

public class ConvertObjectToDto {
    public static UserDto convertUserToUserDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .userName(user.getUserName())
                    .phoneNumber(user.getPhoneNumber())
                    .userRolesDto(user.getUserRoles().stream().map(userRole -> convertUserRolesDto(userRole))
                            .collect(Collectors.toSet()))
                    .build();
        } else {
            return null;
        }

    }

    public static UserRolesDto convertUserRolesDto(UserRoles userRole) {
        if (userRole != null) {
            return UserRolesDto.builder()
                    .id(userRole.getId())
                    .roleDto(convertRoleDto(userRole.getRole()))
                    .build();
        } else {
            return null;
        }
    }

    public static RoleDto convertRoleDto(Roles role) {
        if (role != null) {
            return RoleDto.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .code(role.getCode())
                    .build();
        } else {
            return null;
        }
    }
}
