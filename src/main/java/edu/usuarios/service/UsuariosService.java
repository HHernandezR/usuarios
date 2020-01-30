package edu.usuarios.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import edu.usuarios.data.entity.Usuarios;
import edu.usuarios.payload.usuario.UsuarioRequest;
import edu.usuarios.payload.usuario.UsuarioUpdateRequest;

public interface UsuariosService {
	
	public ResponseEntity<Optional<Usuarios>> saveUsuario(UsuarioRequest usuario);
	public ResponseEntity<Optional<Usuarios>> updateUsuario(UsuarioUpdateRequest usuario);
	public ResponseEntity<Boolean> deleteUsuario(Long idUsuario);
	public Optional<Usuarios> findUsuarioFullName(String nombre, String apaterno, String amaterno);

}
