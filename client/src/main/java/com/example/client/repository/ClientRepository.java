package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.client.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
