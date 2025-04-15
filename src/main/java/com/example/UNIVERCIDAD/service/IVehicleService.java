package com.example.UNIVERCIDAD.service;

import com.example.UNIVERCIDAD.common.VehicleDTO;

import java.util.List;

public interface IVehicleService {
    List<VehicleDTO> findAll();

    List<VehicleDTO> getAvailableLoans();

    VehicleDTO save(VehicleDTO vehicleDTO);
}
