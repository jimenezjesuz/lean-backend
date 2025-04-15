package com.example.UNIVERCIDAD.service.impl;

import com.example.UNIVERCIDAD.common.VehicleDTO;
import com.example.UNIVERCIDAD.common.mapper.VehicleMapper;
import com.example.UNIVERCIDAD.exception.BadRequestException;
import com.example.UNIVERCIDAD.model.Vehicle;
import com.example.UNIVERCIDAD.repository.VehicleRepo;
import com.example.UNIVERCIDAD.service.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {
    private final VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepo.findAll().stream().map(VehicleMapper::toDTO).toList();
    }

    @Override
    public List<VehicleDTO> getAvailableLoans() {
        return vehicleRepo.getAvailableLoans().stream().map(VehicleMapper::toDTO).toList();
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle byCode = vehicleRepo.findByCode(vehicleDTO.getCode());
        if (byCode != null) {
            throw new BadRequestException("El vehículo ya existe");
        }
        return VehicleMapper.toDTO(vehicleRepo.save(VehicleMapper.toEntity(vehicleDTO)));
    }
}
