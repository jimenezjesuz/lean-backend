package com.example.UNIVERCIDAD.service;

import com.example.UNIVERCIDAD.common.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();

    UserDTO findByIdentification(String identification);

    UserDTO save(UserDTO userDTO);
}
