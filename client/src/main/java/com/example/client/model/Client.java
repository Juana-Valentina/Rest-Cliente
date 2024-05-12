package com.example.client.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter

    private long id;

    @Column
    @Getter
    @Setter
    private String nombreCompleto;

    @Column
    @Getter
    @Setter
    private long documentoIdentidad;

    @Column
    @Getter
    @Setter
    private String correoElectronico;

    @Column
    @Getter
    @Setter
    private LocalDate fechaNacimiento;

    @Column
    @Getter
    @Setter
    private String zonaHorariaLocal;



}
