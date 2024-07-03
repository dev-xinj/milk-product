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
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getEmail())
                .phoneNumber(user.getEmail())
                .userRolesDto(user.getUserRoles().stream().map(userRole -> convertUserRolesDto(userRole))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static UserRolesDto convertUserRolesDto(UserRoles userRole) {
        return UserRolesDto.builder()
                .id(userRole.getId())
                .roleDto(convertRoleDto(userRole.getRole()))
                .build();
    }

    public static RoleDto convertRoleDto(Roles role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .build();
    }
}
