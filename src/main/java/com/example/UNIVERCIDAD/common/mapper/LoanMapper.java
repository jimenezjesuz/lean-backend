package com.example.UNIVERCIDAD.common.mapper;

import com.example.UNIVERCIDAD.common.LoanDTO;
import com.example.UNIVERCIDAD.model.Loan;

public class LoanMapper {

    public static LoanDTO toDTO(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .idUser(UserMapper.toDTO(loan.getIdUser()))
                .idVehicle(VehicleMapper.toDTO(loan.getIdVehicle()))
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .status(loan.getStatus())
                .build();
    }

    public static Loan toEntity(LoanDTO loanDTO) {
        return Loan.builder()
                .id(loanDTO.getId())
                .idUser(UserMapper.toEntity(loanDTO.getIdUser()))
                .idVehicle(VehicleMapper.toEntity(loanDTO.getIdVehicle()))
                .startDate(loanDTO.getStartDate())
                .endDate(loanDTO.getEndDate())
                .status(loanDTO.getStatus())
                .build();
    }
}
