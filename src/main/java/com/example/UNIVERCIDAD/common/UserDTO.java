package com.example.UNIVERCIDAD.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String identification;
    private String email;
}
