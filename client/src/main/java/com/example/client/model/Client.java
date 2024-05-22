package com.example.client.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// @Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
@Getter 
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")

    private long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "documento_identidad")
    private long documentoIdentidad;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "zona_horaria_local")
    private String zonaHorariaLocal;

}

