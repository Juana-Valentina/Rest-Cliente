package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT nombre_completo FROM cliente WHERE correo_electronico = vale@example.com", nativeQuery = true)
    String findNombreCompletoByCorreoElectronico(String correoElectronico);
}
