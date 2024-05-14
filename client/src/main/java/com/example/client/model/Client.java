package com.example.client.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
@Table(name = "cliente")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter
    @Column(name = "id")

    private long id;

    @Column(name = "nombre_completo")
    @Getter
    @Setter
    private String nombreCompleto;

    @Column(name = "documento_identidad")
    @Getter
    @Setter
    private long documentoIdentidad;

    @Column(name = "correo_electronico")
    @Getter
    @Setter
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    @Getter
    @Setter
    private LocalDate fechaNacimiento;

    @Column(name = "zona_horaria_local")
    @Getter
    @Setter
    private String zonaHorariaLocal;

    /** Constructor sin parámetros. Necesario para JPA. */
    public Client() {}

    /** Constructor con parámetros para inicializar un cliente con todos sus atributos. */
    public Client(String nombreCompleto, long documentoIdentidad, String correoElectronico, LocalDate fechaNacimiento, String zonaHorariaLocal) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.zonaHorariaLocal = zonaHorariaLocal;
    }

    /** Calcula la edad del cliente basado en su fecha de nacimiento. */
    public int getEdad() {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(this.fechaNacimiento, fechaActual);
        return periodo.getYears();
    }

}

