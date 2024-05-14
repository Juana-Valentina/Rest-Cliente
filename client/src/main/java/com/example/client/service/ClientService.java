package com.example.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;


@Service

public class ClientService {

    private final ClientRepository clientRepository;
    
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClient(){
        return this.clientRepository.findAll();
    }


    public Client save(Client client){
        return this.clientRepository.save(client);
    }

    public Optional<Client> getById(Long id){
        return this.clientRepository.findById(id);
    }

    public Client update(Long id, Client client){
        Optional<Client> optionalClient = getById(id);
        if (optionalClient.isPresent()) {
            Client clientExistente = optionalClient.get();
            clientExistente.setNombreCompleto(client.getNombreCompleto());
            clientExistente.setDocumentoIdentidad(client.getDocumentoIdentidad());
            clientExistente.setCorreoElectronico(client.getCorreoElectronico());
            clientExistente.setFechaNacimiento(client.getFechaNacimiento());
            clientExistente.setZonaHorariaLocal(client.getZonaHorariaLocal());
            return this.clientRepository.save(clientExistente); // Guardar los cambios
        } else {
            return null;
        }
    }

    public void delete(Long id){
        this.clientRepository.deleteById(id);
    }

    public List<Client> getClientesOrdenadosPorNombre() {
        return this.clientRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCompleto"));
    }

    

}
