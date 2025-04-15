package com.example.UNIVERCIDAD;

import com.example.UNIVERCIDAD.common.Status;
import com.example.UNIVERCIDAD.model.Loan;
import com.example.UNIVERCIDAD.model.User;
import com.example.UNIVERCIDAD.model.Vehicle;
import com.example.UNIVERCIDAD.repository.LoanRepo;
import com.example.UNIVERCIDAD.repository.UserRepo;
import com.example.UNIVERCIDAD.repository.VehicleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class UnivercidadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnivercidadApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(VehicleRepo vehicleRepo, UserRepo userRepo, LoanRepo loanRepo) {
        return args -> {
            Vehicle vehiculo1 = new Vehicle(null, "Roja nueve velocidades", "Ruta", "VEH001");
            Vehicle vehiculo2 = new Vehicle(null, "Roja 12 velocidades", "Montaña", "VEH002");
            Vehicle vehiculo3 = new Vehicle(null, "Azul/plata monoplato", "Montaña", "VEH003");
            vehicleRepo.save(vehiculo1);
            vehicleRepo.save(vehiculo2);
            vehicleRepo.save(vehiculo3);

            User usuario1 = new User(null, "Juan", "Pérez", "12345678A", "juan@example.com");
            User usuario2 = new User(null, "Pedro", "Lopez", "12345378A", "pedro@example.com");
            User usuario3 = new User(null, "Raul", "Jiménez", "12322678A", "raul@example.com");
            userRepo.save(usuario1);
            userRepo.save(usuario2);
            userRepo.save(usuario3);

            Loan prestamo = new Loan(null, new Date(), new Date(), Status.EN_PROCESO.name(), usuario1, vehiculo1);
            Loan prestamo1 = new Loan(null, new Date(), new Date(), Status.COMPLETADO.name(), usuario2, vehiculo2);
            Loan prestamo2 = new Loan(null, new Date(), new Date(), Status.COMPLETADO.name(), usuario3, vehiculo3);
            loanRepo.save(prestamo);
            loanRepo.save(prestamo1);
            loanRepo.save(prestamo2);
        };
    }
}
