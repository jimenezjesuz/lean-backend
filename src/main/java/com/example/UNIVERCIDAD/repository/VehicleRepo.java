package com.example.UNIVERCIDAD.repository;

import com.example.UNIVERCIDAD.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query(value = """
            select
            \tv.*
            from
            \tvehicle v
            left join loan p on
            \tv.id_vehicle = p.id_vehicle
            \tand p.status not in ('CANCELADO', 'COMPLETADO')
            where
            \tp.id_loan is null""", nativeQuery = true)
    List<Vehicle> getAvailableLoans();

    Vehicle findByCode(String code);

}
