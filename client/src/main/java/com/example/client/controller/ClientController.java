package com.example.client.controller;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> clientes-promedio

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.model.Client;
import com.example.client.service.ClientService;


@RestController
@RequestMapping("/api/cliente")

public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public String verificarConexion() {
        return "¡Servidor conectado!";
    }

    @GetMapping("/listado")
    public List<Client> obtenerTodosLosClientes() {
        return clientService.getClient();
    }
    
    @GetMapping("/listado-ordenado")
    public ResponseEntity<List<Client>> obtenerTodosLosClientesOrdenados() {
        List<Client> clientes = clientService.getClientesOrdenadosPorNombre();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

<<<<<<< HEAD
    
=======
    @GetMapping("/listado-ordenado-por-edad")
    public ResponseEntity<List<Client>> obtenerClientesOrdenadosPorEdad() {
        List<Client> clientesOrdenadosPorEdad = clientService.obtenerClientesOrdenadosPorEdad();
        return new ResponseEntity<>(clientesOrdenadosPorEdad, HttpStatus.OK);
    }

    @GetMapping("/Cantidad-promedio")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticasClientes() {
        Map<String, Object> estadisticas = new HashMap<>();
        List<Client> clientes = clientService.getClient();
        estadisticas.put("cantidadClientes", clientes.size());
        estadisticas.put("promedioEdad", clientService.calcularPromedioEdad());
        return ResponseEntity.ok(estadisticas);
    }
>>>>>>> clientes-promedio
    
    @PostMapping("/guardar")
    public ResponseEntity<String> crearCliente(
        @RequestParam String nombreCompleto,
        @RequestParam long documentoIdentidad,
        @RequestParam String correoElectronico,
        @RequestParam LocalDate fechaNacimiento,
        @RequestParam String zonaHorariaLocal
        ) {
            Client client = new Client();
            client.setNombreCompleto(nombreCompleto);
            client.setDocumentoIdentidad(documentoIdentidad);
            client.setCorreoElectronico(correoElectronico);
            client.setFechaNacimiento(fechaNacimiento);
            client.setZonaHorariaLocal(zonaHorariaLocal);
            
            clientService.save(client);
            return ResponseEntity.ok("Cliente guardado correctamente");
        }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> actualizarCliente(
        @PathVariable long id,
        @RequestParam String nombreCompleto,
        @RequestParam long documentoIdentidad,
        @RequestParam String correoElectronico,
        @RequestParam LocalDate fechaNacimiento,
        @RequestParam String zonaHorariaLocal
        ) {
            Client client = new Client();
            client.setNombreCompleto(nombreCompleto);
            client.setDocumentoIdentidad(documentoIdentidad);
            client.setCorreoElectronico(correoElectronico);
            client.setFechaNacimiento(fechaNacimiento);
            client.setZonaHorariaLocal(zonaHorariaLocal);
            
            Client clienteActualizado = clientService.update(id, client);
            if (clienteActualizado != null) {
                return ResponseEntity.ok("Cliente actualizado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún cliente con el ID especificado");
            }
        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado correctamente");
    } 

    
}