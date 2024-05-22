package com.example.client.interfaces;

import java.util.List;

import com.example.client.dto.ClientDTO;

public interface ClientInterface {
    
    public ClientDTO guardarCliente(ClientDTO clientDTO);
    public List<ClientDTO> obtenerTodosLosClientes();
    public List<ClientDTO> obtenerClientesOrdenadosAlfabeticamente();
    public List<ClientDTO> obtenerClientesOrdenadosPorEdad();
    public int contarClientes();
    public double calcularPromedioEdadClientes();
    public ClientDTO actualizarCliente(ClientDTO clientDTO);
    public void eliminarCliente(Long id);

}