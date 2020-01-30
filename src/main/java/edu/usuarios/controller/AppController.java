package edu.usuarios.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {
	
	private static final Logger LOGGER = LogManager.getLogger(AppController.class);
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealth(){
		String mensaje = "SERVICIO USUARIOS ACTIVO";
		LOGGER.info(mensaje);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	}

}
