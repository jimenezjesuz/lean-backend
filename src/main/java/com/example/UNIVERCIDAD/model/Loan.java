package com.example.UNIVERCIDAD.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan", updatable = false)
    private Long id;
    private Date endDate;
    private Date startDate;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User idUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id_vehicle")
    private Vehicle idVehicle;

}
