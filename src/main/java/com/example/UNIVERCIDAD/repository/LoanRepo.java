package com.example.UNIVERCIDAD.repository;

import com.example.UNIVERCIDAD.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {


    @Query(value = """
                SELECT * FROM loan
                WHERE CAST(start_date AS DATE) = :fecha
            """, nativeQuery = true)
    List<Loan> findByStartDateWithoutTime(@Param("fecha") Date fecha);

    @Query(value = """
                SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
                FROM loan
                WHERE id_user = :idUsuario
                  AND status = 'EN_PROCESO'
            """, nativeQuery = true)
    boolean isUserWithActiveLoan(@Param("idUsuario") Long idUsuario);

    @Query(value = """
                SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
                FROM loan
                WHERE id_vehicle = :idVehiculo
                  AND status = 'EN_PROCESO'
            """, nativeQuery = true)
    boolean isVehicleWithActiveLoan(@Param("idVehiculo") Long idVehiculo);

}
