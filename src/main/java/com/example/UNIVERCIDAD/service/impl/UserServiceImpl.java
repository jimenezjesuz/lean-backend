package com.example.UNIVERCIDAD.service.impl;

import com.example.UNIVERCIDAD.common.UserDTO;
import com.example.UNIVERCIDAD.common.mapper.UserMapper;
import com.example.UNIVERCIDAD.exception.BadRequestException;
import com.example.UNIVERCIDAD.exception.NotFoundException;
import com.example.UNIVERCIDAD.model.User;
import com.example.UNIVERCIDAD.repository.UserRepo;
import com.example.UNIVERCIDAD.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepo.findAll().stream().map(UserMapper::toDTO).toList();
    }

    @Override
    public UserDTO findByIdentification(String identification) {
        User byIdentification = userRepo.findByIdentification(identification);
        if (byIdentification == null) {
            throw new NotFoundException("No existe un usuario con esa identificación");
        }
        return UserMapper.toDTO(byIdentification);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userRepo.findByIdentification(userDTO.getIdentification());
        if (user != null) {
            throw new BadRequestException("Ya existe un usuario con esa identificación");
        }
        user = userRepo.findByEmail(userDTO.getEmail());
        if (user != null) {
            throw new BadRequestException("Ya existe un usuario con ese correo");
        }
        return UserMapper.toDTO(userRepo.save(UserMapper.toEntity(userDTO)));
    }
}
