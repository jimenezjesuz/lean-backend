package com.example.UNIVERCIDAD.controller;

import com.example.UNIVERCIDAD.common.VehicleDTO;
import com.example.UNIVERCIDAD.service.IVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final IVehicleService iVehicleService;

    public VehicleController(IVehicleService iVehicleService) {
        this.iVehicleService = iVehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok().body(iVehicleService.findAll());
    }


    @GetMapping("/available")
    public ResponseEntity<?> getAvailableVehicles() {
        return ResponseEntity.ok().body(iVehicleService.getAvailableLoans());
    }

    @PostMapping
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok().body(iVehicleService.save(vehicleDTO));
    }
}
