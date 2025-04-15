package com.example.UNIVERCIDAD.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDTO {
    private Long id;
    private String code;
    private String type;
    private String description;
}
