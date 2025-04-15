package com.example.UNIVERCIDAD.service.impl;

import com.example.UNIVERCIDAD.common.LoanDTO;
import com.example.UNIVERCIDAD.common.Status;
import com.example.UNIVERCIDAD.common.mapper.LoanMapper;
import com.example.UNIVERCIDAD.common.mapper.VehicleMapper;
import com.example.UNIVERCIDAD.exception.BadRequestException;
import com.example.UNIVERCIDAD.exception.NotFoundException;
import com.example.UNIVERCIDAD.model.Loan;
import com.example.UNIVERCIDAD.model.Vehicle;
import com.example.UNIVERCIDAD.repository.LoanRepo;
import com.example.UNIVERCIDAD.repository.VehicleRepo;
import com.example.UNIVERCIDAD.service.ILoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements ILoanService {
    private final LoanRepo loanRepo;
    private final VehicleRepo vehicleRepo;

    public LoanServiceImpl(LoanRepo loanRepo, VehicleRepo vehicleRepo) {
        this.loanRepo = loanRepo;
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public LoanDTO findLastLoanByIdentification(String identification) {
        return null;
    }

    @Override
    public List<LoanDTO> findLoansByTodayDate() {
        LocalDate localDate = LocalDate.now(); // hoy sin hora
        Date dateSinHora = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return loanRepo.findByStartDateWithoutTime(dateSinHora).stream().map(LoanMapper::toDTO).toList();
    }

    @Override
    public List<LoanDTO> findAll() {
        return loanRepo.findAll().stream().map(LoanMapper::toDTO).toList();
    }

    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        if (loanDTO.getIdVehicle() == null) {
            throw new BadRequestException("El vehículo no fué seleccionado");
        }
        if (loanDTO.getIdUser() == null) {
            throw new BadRequestException("El usuario no fué seleccionado");
        }
        boolean userWithActiveLoan = loanRepo.isUserWithActiveLoan(loanDTO.getIdUser().getId());
        if (userWithActiveLoan) {
            throw new BadRequestException("El usuario ya tiene un préstamo activo");
        }
        boolean vehicleWithActiveLoan = loanRepo.isVehicleWithActiveLoan(loanDTO.getIdVehicle().getId());
        if (vehicleWithActiveLoan) {
            throw new BadRequestException("El vehículo ya tiene un préstamo activo");
        }
        Loan entity = LoanMapper.toEntity(loanDTO);
        entity.setStartDate(new Date());
        entity.setEndDate(null);
        entity.setStatus(Status.EN_PROCESO.name());
        return LoanMapper.toDTO(loanRepo.save(entity));
    }

    @Override
    public List<LoanDTO> getAvailableLoans() {
        List<Vehicle> availableLoans = vehicleRepo.getAvailableLoans();
        return availableLoans.stream()
                .map(VehicleMapper::toDTO)
                .map(vehicle -> LoanDTO.builder()
                        .idVehicle(vehicle)
                        .startDate(new Date())
                        .endDate(new Date())
                        .status(Status.PENDIENTE.name())
                        .build())
                .toList();
    }

    @Override
    public LoanDTO cancelLoan(long id) {
        return loanRepo.findById(id).map(loan -> {
            // Si ya está cancelado o finalizado, no lo cambiamos
            if ("CANCELADO".equalsIgnoreCase(loan.getStatus()) ||
                    "COMPLETADO".equalsIgnoreCase(loan.getStatus())) {
                return LoanMapper.toDTO(loan);
            }
            loan.setStatus(Status.CANCELADO.name());
            loan.setEndDate(new Date());
            return LoanMapper.toDTO(loanRepo.save(loan));
        }).orElseThrow(() -> new NotFoundException("No se encontró el préstamo"));
    }
}
