package edu.usuarios.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import edu.usuarios.data.entity.Usuarios;
import edu.usuarios.payload.usuario.UsuarioRequest;
import edu.usuarios.payload.usuario.UsuarioUpdateRequest;
import edu.usuarios.service.UsuariosService;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuariosController {
	
	@Autowired
	private UsuariosService usuarioService;
	
	@PostMapping("/save")
	public ResponseEntity<Optional<Usuarios>> saveUsuario(@Valid @RequestBody UsuarioRequest usuario){
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Optional<Usuarios>> updateUsuario(@Valid @RequestBody UsuarioUpdateRequest usuario){
		return usuarioService.updateUsuario(usuario);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<Boolean> deleteUsuario(@RequestParam(required = true) @Min(value = 1) Long idUsuario){
		return usuarioService.deleteUsuario(idUsuario);
	}
	
	@GetMapping("/find")
	public ResponseEntity<Optional<Usuarios>> findUsuarioFullName(@RequestParam(required = true) @NotEmpty @NotNull String nombre, @NotEmpty @NotNull String aPaterno, @NotEmpty @NotNull String aMaterno){
		return usuarioService.findUsuarioFullName(nombre, aPaterno, aMaterno);
	}

}
