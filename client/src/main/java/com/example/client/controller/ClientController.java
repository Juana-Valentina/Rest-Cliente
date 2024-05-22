package com.example.client.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.dto.ClientDTO;
import com.example.client.service.ClientService;

@RestController
@RequestMapping("/api/cliente")

public class ClientController {
    
    private final ClientService clientService;

    /** Constructor para inyectar el servicio de cliente. */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /** Verifica la conexión con el servidor. */
    @GetMapping
    public String verificarConexion() {
        return "¡Servidor conectado!";
    }

    /** Obtiene una lista de todos los clientes. */
    @GetMapping("/listado")
    public List<ClientDTO> obtenerTodosLosClientes() {
        try {
            return clientService.obtenerTodosLosClientes();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener todos los clientes", exception);
        }
    }

    /** Obtiene una lista de clientes ordenados por nombre completo en orden ascendente. */
    @GetMapping("/listado-ordenado")
    public List<ClientDTO> obtenerClientesOrdenadosAlfabeticamente() {
        try {
            return clientService.obtenerClientesOrdenadosAlfabeticamente();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener clientes ordenados alfabéticamente", exception);
        }
    }

    /** Obtiene una lista de clientes ordenados por edad en orden ascendente. */
    @GetMapping("/listado-ordenado-por-edad")
    public List<ClientDTO> obtenerClientesOrdenadosPorEdad() {
        try {
            return clientService.obtenerClientesOrdenadosPorEdad();
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener clientes ordenados por edad", exception);
        }
    }

    /** Obtiene estadísticas sobre los clientes, como la cantidad total y el promedio de edad. */
    @GetMapping("/Cantidad-promedio")
    public String obtenerCantidadPromedioClientes() {
        try {
            int cantidadClientes = clientService.contarClientes();
            double promedioEdad = clientService.calcularPromedioEdadClientes();
            return "Cantidad de clientes: " + cantidadClientes + ", Promedio de edad: " + promedioEdad;
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al obtener la cantidad y el promedio de edad de los clientes", exception);
        }
    }
 
    /** Crea un nuevo cliente con los datos proporcionados. */
    @PostMapping("/guardar")
    public ClientDTO guardarCliente(@RequestBody ClientDTO clienteDTO) {
        try {
            return clientService.guardarCliente(clienteDTO);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al guardar el cliente", exception);
        }
    }

    /** Actualiza un cliente existente con los datos proporcionados. */
    @PutMapping("/editar/{id}")
    public ClientDTO actualizarCliente(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientDTO.setId(id);
            return clientService.actualizarCliente(clientDTO);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al actualizar el cliente", exception);
        }
    }

    /** Elimina un cliente por su ID. */
    @DeleteMapping("/eliminar/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        try {
            clientService.eliminarCliente(id);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Error al eliminar el cliente", exception);
        }
    }

    //otras formas:
    // @GetMapping("/clientes")
    // public List<ClientDTO> obtenerClientesPorNombre(@RequestParam String nombre) {
    //     return clientService.obtenerClientesPorNombre(nombre);
    // }

    // @PostMapping("/clientes")
    // public ClientDTO guardarCliente(@RequestBody ClientDTO clienteDTO) {
    //     return clientService.guardarCliente(clienteDTO);
    // }

    // @GetMapping("/clientes/{id}")
    // public ClientDTO obtenerClientePorId(@PathVariable Long id) {
    //     return clientService.obtenerClientePorId(id);
    // }

    // @GetMapping("/clientes")
    // public List<ClientDTO> obtenerClientesPorEdad(@RequestParam int edad) {
    //     return clientService.obtenerClientesPorEdad(edad);
    // }

    // @GetMapping("/clientes")
    // public List<ClientDTO> obtenerClientesPorFiltro(@RequestParam String nombre, @RequestParam int edad) {
    //     return clientService.obtenerClientesPorFiltro(nombre, edad);
    // }

    // @PutMapping("/clientes/{id}")
    // public ClientDTO actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
    //     clienteDTO.setId(id);
    //     return clientService.actualizarCliente(clienteDTO);
    // }

}