package com.example.client.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;




@RestController
@RequestMapping("/api/cliente")

public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping()
    public String index() {
        return "CONECTADO";
    }

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    @PostMapping("/grabar")
    public String saveClient(@RequestBody Client client) {
        if (client != null) {
            clientRepository.save(client);
            return "Cliente guardado correctamente";
        } else {
            return "Error: No se proporcionó un cliente válido";
        }
    }

    @PutMapping("/editar/{id}")
    public String update(@PathVariable long id, @RequestBody Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        
        if (optionalClient.isPresent()) {
            Client updateClient = optionalClient.get();
            updateClient.setNombreCompleto(client.getNombreCompleto());
            updateClient.setDocumentoIdentidad(client.getDocumentoIdentidad());
            updateClient.setCorreoElectronico(client.getCorreoElectronico());
            updateClient.setFechaNacimiento(client.getFechaNacimiento());
            updateClient.setZonaHorariaLocal(client.getZonaHorariaLocal()); // Aquí se asigna la zona horaria directamente como una cadena de texto
            clientRepository.save(updateClient);
            return "Cliente editado correctamente";
        } else {
            return "No se encontró ningún cliente con el ID especificado";
        }
    }

    
}