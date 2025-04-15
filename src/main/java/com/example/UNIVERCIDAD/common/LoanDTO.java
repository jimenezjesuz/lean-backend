package com.example.UNIVERCIDAD.common;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoanDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String status;
    private UserDTO idUser;
    private VehicleDTO idVehicle;
}
