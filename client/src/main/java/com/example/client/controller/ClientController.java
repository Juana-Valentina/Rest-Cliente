package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    
}