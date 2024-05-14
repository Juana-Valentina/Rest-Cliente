package com.example.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;

@Service

public class ClientService {

    private final ClientRepository clientRepository;
    
    // Constructor para inyectar la dependencia de ClientRepository
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    /** Obtiene la lista completa de clientes desde el repositorio. */
    public List<Client> getClient(){
        return this.clientRepository.findAll();
    }

    /** Guarda un nuevo cliente en el repositorio. */
    public Client save(Client client){
        return this.clientRepository.save(client);
    }

    /** Busca un cliente por su ID.  */
    public Optional<Client> getById(Long id){
        return this.clientRepository.findById(id);
    }

    /** Actualiza un cliente existente con nuevos datos.  */
    public Client update(Client updatedClient) {
        return this.clientRepository.save(updatedClient);
    }

    /** Elimina un cliente por su ID. */
    public void delete(Long id){
        this.clientRepository.deleteById(id);
    }

    /** Obtiene una lista de clientes ordenados por su nombre completo en orden ascendente. */
    public List<Client> getClientesOrdenadosPorNombre() {
        return this.clientRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCompleto"));
    }

    /** Obtiene una lista de clientes ordenados por su edad en orden ascendente. */
    public List<Client> obtenerClientesOrdenadosPorEdad() {
        List<Client> clientes = getClient();
        return clientes.stream()
        .sorted((c1, c2) -> c1.getEdad() - c2.getEdad())
        .collect(Collectors.toList());
    }

    /** Calcula el promedio de edad de todos los clientes. */
    public double calcularPromedioEdad() {
        List<Client> clientes = getClient();
        int totalEdad = clientes.stream()
                .mapToInt(Client::getEdad)
                .sum();
        return (double) totalEdad / clientes.size();
    }

}
