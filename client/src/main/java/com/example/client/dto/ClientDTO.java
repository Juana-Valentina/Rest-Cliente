package com.example.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor

public class ClientDTO {
    @Getter @Setter private String nombreCompleto;
    @Getter @Setter private String documentoIdentidad;
    @Getter @Setter private String correoElectronico;
    @Getter @Setter private LocalDateTime fechaNacimiento;

    /** Constructor sin parámetros. Necesario para la deserialización. */
    public ClientDTO() {}

    /** Constructor con parámetros para inicializar el DTO con todos sus atributos. */
    public ClientDTO(String nombreCompleto, String documentoIdentidad, String correoElectronico, LocalDateTime fechaNacimiento) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
    }
}

