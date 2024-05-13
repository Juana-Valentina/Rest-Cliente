package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // Indica que esta es una aplicación Spring Boot.
@RestController // Marca esta clase como un controlador REST, capaz de manejar solicitudes HTTP.
public class ClientApplication {

	/**   Método principal que sirve como punto de entrada de la aplicación Spring Boot.
	 *  Llama al método run de la clase SpringApplication para iniciar la aplicación.
     */
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
