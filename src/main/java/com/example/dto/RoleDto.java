package com.example.dto;

import com.example.entities.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private int id;
    private RoleType roleType;
}