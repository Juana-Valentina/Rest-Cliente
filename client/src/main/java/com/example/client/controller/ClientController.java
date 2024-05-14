package com.example.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.model.Client;
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
    public List<Client> obtenerTodosLosClientes() {
        return clientService.getClient();
    }
    
    /** Obtiene una lista de clientes ordenados por nombre completo en orden ascendente. */
    @GetMapping("/listado-ordenado")
    public ResponseEntity<List<Client>> obtenerTodosLosClientesOrdenados() {
        List<Client> clientes = clientService.getClientesOrdenadosPorNombre();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /** Obtiene una lista de clientes ordenados por edad en orden ascendente. */
    @GetMapping("/listado-ordenado-por-edad")
    public ResponseEntity<List<Client>> obtenerClientesOrdenadosPorEdad() {
        List<Client> clientesOrdenadosPorEdad = clientService.obtenerClientesOrdenadosPorEdad();
        return new ResponseEntity<>(clientesOrdenadosPorEdad, HttpStatus.OK);
    }

    /** Obtiene estadísticas sobre los clientes, como la cantidad total y el promedio de edad. */
    @GetMapping("/Cantidad-promedio")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticasClientes() {
        Map<String, Object> estadisticas = new HashMap<>();
        List<Client> clientes = clientService.getClient();
        estadisticas.put("cantidadClientes", clientes.size());
        estadisticas.put("promedioEdad", clientService.calcularPromedioEdad());
        return ResponseEntity.ok(estadisticas);
    }
    
    /** Crea un nuevo cliente con los datos proporcionados. */
    @PostMapping("/guardar")
    public ResponseEntity<String> crearCliente(@RequestBody Client cliente) {
        if (cliente != null) {
            Client clienteGuardado = clientService.save(cliente);
            if (clienteGuardado != null) {
                return new ResponseEntity<>("Cliente creado exitosamente", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Error al guardar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("El objeto cliente es nulo", HttpStatus.BAD_REQUEST);
        }
    }

    /** Actualiza un cliente existente con los datos proporcionados. */
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @RequestBody Client clienteActualizado) {
        Optional<Client> optionalClient = clientService.getById(id);
        if (optionalClient.isPresent()) {
            Client clientEncontrado = optionalClient.get();
            clientEncontrado.setNombreCompleto(clienteActualizado.getNombreCompleto());
            clientEncontrado.setDocumentoIdentidad(clienteActualizado.getDocumentoIdentidad());
            clientEncontrado.setCorreoElectronico(clienteActualizado.getCorreoElectronico());
            clientEncontrado.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            clientEncontrado.setZonaHorariaLocal(clienteActualizado.getZonaHorariaLocal());

            clientService.update(clientEncontrado);
            return new ResponseEntity<>("Cliente actualizado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /** Elimina un cliente por su ID. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado correctamente");
    } 

    
}