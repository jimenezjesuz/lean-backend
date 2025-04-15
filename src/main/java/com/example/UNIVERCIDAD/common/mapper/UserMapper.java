package com.example.UNIVERCIDAD.common.mapper;

import com.example.UNIVERCIDAD.common.UserDTO;
import com.example.UNIVERCIDAD.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .identification(user.getIdentification())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .identification(userDTO.getIdentification())
                .email(userDTO.getEmail())
                .build();
    }
}
