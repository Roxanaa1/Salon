package com.example.controller;

import com.example.dto.UserDto;
import com.example.dto.validation.LoginValidation;
import com.example.dto.validation.ValidationOrder;
import com.example.entities.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated(ValidationOrder.class) UserDto userDto) {
        User userEntity = UserMapper.toEntity(userDto);
        User createdUser = userService.create(userEntity);
        UserDto createdUserDTO = UserMapper.toDto(createdUser);
        return ResponseEntity.ok(createdUserDTO);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String token) {
        userService.verify(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated(LoginValidation.class) UserDto userDto) {
        User userToLogin = UserMapper.toEntity(userDto);
        User user = userService.login(userToLogin.getEmail(), userToLogin.getPassword());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
}