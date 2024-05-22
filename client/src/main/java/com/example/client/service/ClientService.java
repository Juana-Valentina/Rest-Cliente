package com.example.client.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List; //almacenar conjuntos de elementos
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.client.dto.ClientDTO;
import com.example.client.interfaces.ClientInterface;
import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;

@Service
public class ClientService implements ClientInterface {


    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO guardarCliente(ClientDTO clienteDTO) {
        try {
            Client clienteEntity = new Client();
            clienteEntity.setNombreCompleto(clienteDTO.getNombreCompleto());
            clienteEntity.setDocumentoIdentidad(clienteDTO.getDocumentoIdentidad());
            clienteEntity.setCorreoElectronico(clienteDTO.getCorreoElectronico());
            clienteEntity.setFechaNacimiento(clienteDTO.getFechaNacimiento());
            clienteEntity.setZonaHorariaLocal(clienteDTO.getZonaHorariaLocal());
    
            clienteEntity = clientRepository.save(clienteEntity);
    
            return convertirAClienteDTO(clienteEntity);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al guardar el cliente", exception);
        }
    }

    @Override
    public List<ClientDTO> obtenerTodosLosClientes() {
        try {
            List<Client> clientes = clientRepository.findAll();
            return clientes.stream().map(this::convertirAClienteDTO).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener todos los clientes", exception);
        }
    }

    @Override
    public List<ClientDTO> obtenerClientesOrdenadosAlfabeticamente() {
        try {
            List<Client> clientes = clientRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCompleto"));
            return clientes.stream().map(this::convertirAClienteDTO).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener clientes ordenados alfabéticamente", exception);
        }
    }

    @Override
    public List<ClientDTO> obtenerClientesOrdenadosPorEdad() {
        try {
            List<Client> clientes = clientRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaNacimiento"));
            return clientes.stream().map(this::convertirAClienteDTO).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener clientes ordenados por edad", exception);
        }
    }

    @Override
    public int contarClientes() {
        try {
            return (int) clientRepository.count();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al contar clientes", exception);
        }
    }

    @Override
    public double calcularPromedioEdadClientes() {
        try {
            List<Client> clientes = clientRepository.findAll();
            int totalEdades = clientes.stream()
                    .mapToInt(cliente -> Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears())
                    .sum();
            return (double) totalEdades / clientes.size();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al calcular el promedio de edad de los clientes", exception);
        }
    }

    @Override
    public ClientDTO actualizarCliente(ClientDTO clienteDTO) {
        try {
            Client clienteEntity = clientRepository.findById(clienteDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            clienteEntity.setNombreCompleto(clienteDTO.getNombreCompleto());
            clienteEntity.setDocumentoIdentidad(clienteDTO.getDocumentoIdentidad()); 
            clienteEntity.setCorreoElectronico(clienteDTO.getCorreoElectronico());
            clienteEntity.setFechaNacimiento(clienteDTO.getFechaNacimiento());
            clienteEntity.setZonaHorariaLocal(clienteDTO.getZonaHorariaLocal());


            clienteEntity = clientRepository.save(clienteEntity);

            return convertirAClienteDTO(clienteEntity);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al actualizar el cliente", exception);
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al eliminar el cliente", exception);
        }
    }

    private ClientDTO convertirAClienteDTO(Client cliente) {
        ClientDTO clienteDTO = new ClientDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombreCompleto(cliente.getNombreCompleto());
        clienteDTO.setDocumentoIdentidad(cliente.getDocumentoIdentidad()); 
        clienteDTO.setCorreoElectronico(cliente.getCorreoElectronico());
        clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteDTO.setEdad(Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears());
        clienteDTO.setZonaHorariaLocal(cliente.getZonaHorariaLocal()); 
        return clienteDTO;
    }
    
}