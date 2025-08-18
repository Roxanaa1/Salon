package com.example.mapper;

import com.example.dto.RoleDto;
import com.example.dto.UserDto;
import com.example.entities.Role;
import com.example.entities.User;

public class UserMapper {
    public static User toEntity(UserDto userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setCountry(userDTO.getCountry());
        user.setVerifiedAccount(userDTO.getVerifiedAccount());
        user.setVerificationToken(userDTO.getVerificationToken());

        if (userDTO.getRole() != null) {
            Role role = new Role();
            role.setId(userDTO.getRole().getId());
            role.setRoleType(userDTO.getRole().getRoleType());
            user.setRole(role);
        }

        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setYearOfBirth(user.getYearOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setCountry(user.getCountry());
        userDTO.setVerifiedAccount(user.getVerifiedAccount());
        userDTO.setVerificationToken(user.getVerificationToken());

        if (user.getRole() != null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(user.getRole().getId());
            roleDto.setRoleType(user.getRole().getRoleType());
            userDTO.setRole(roleDto);
        }

        return userDTO;
    }
}
