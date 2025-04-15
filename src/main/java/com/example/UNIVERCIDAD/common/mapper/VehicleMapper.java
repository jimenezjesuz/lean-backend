package com.example.UNIVERCIDAD.common.mapper;

import com.example.UNIVERCIDAD.common.VehicleDTO;
import com.example.UNIVERCIDAD.model.Vehicle;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .code(vehicle.getCode())
                .type(vehicle.getType())
                .description(vehicle.getDescription())
                .build();
    }

    public static Vehicle toEntity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return null;
        }
        return Vehicle.builder()
                .id(vehicleDTO.getId())
                .code(vehicleDTO.getCode())
                .type(vehicleDTO.getType())
                .description(vehicleDTO.getDescription())
                .build();
    }
}
