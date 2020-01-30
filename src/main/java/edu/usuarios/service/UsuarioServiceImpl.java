package edu.usuarios.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.usuarios.data.dao.UsuariosDao;
import edu.usuarios.data.entity.Usuarios;
import edu.usuarios.payload.usuario.UsuarioRequest;
import edu.usuarios.payload.usuario.UsuarioUpdateRequest;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuariosService {

	private static final Logger LOGGER = LogManager.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuariosDao usuariosDao;

	@Override
	public ResponseEntity<Optional<Usuarios>> saveUsuario(UsuarioRequest usuario) {

		LOGGER.info("STARTING SAVE USUARIO - {}", usuario.toString());
		ResponseEntity<Optional<Usuarios>> result = null;
		Optional<Usuarios> optUsuario = Optional.empty();

		try {

			Usuarios usuarioReq = new Usuarios();
			usuarioReq.setNombre(usuario.getNombre().toUpperCase().trim());
			usuarioReq.setAPaterno(usuario.getAPaterno().toUpperCase().trim());
			usuarioReq.setAMaterno(usuario.getAMaterno().toUpperCase().trim());

			Usuarios usuarioResp = usuariosDao.save(usuarioReq);
			optUsuario = Optional.of(usuarioResp);

			LOGGER.info("END SAVE USUARIO - {}", optUsuario.get().toString());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("ERROR SAVE USUARIO - {}", e.getLocalizedMessage());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@Override
	public ResponseEntity<Optional<Usuarios>> updateUsuario(UsuarioUpdateRequest usuarioRequest) {

		LOGGER.info("STARTING UPDATE USUARIO - {}", usuarioRequest.toString());
		ResponseEntity<Optional<Usuarios>> result = null;
		Optional<Usuarios> optUsuario = Optional.empty();

		try {

			Optional<Usuarios> usuario = usuariosDao.findUsuarioId(usuarioRequest.getIdUsuario());
			if (usuario.isPresent()) {
				Usuarios usuarioInsert = new Usuarios();

				usuarioInsert.setIdUsuario(usuarioRequest.getIdUsuario());
				usuarioInsert.setNombre(usuarioRequest.getNombre());
				usuarioInsert.setAPaterno(usuarioRequest.getAPaterno());
				usuarioInsert.setAMaterno(usuarioRequest.getAMaterno());

				Usuarios usuarioResult = usuariosDao.save(usuarioInsert);
				optUsuario = Optional.of(usuarioResult);

				LOGGER.info("END UPDATE USUARIO - {}", optUsuario.get().toString());
				result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.OK);
			} else {
				LOGGER.error("ERROR UPDATE USUARIO, USUARIO NO ENCONTRADO - {}", usuarioRequest.toString());
				result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			LOGGER.error("ERROR UPDATE USUARIO - {}", e.getLocalizedMessage());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@Override
	public ResponseEntity<Boolean> deleteUsuario(Long idUsuario) {

		LOGGER.info("STARTING DELETE USUARIO - {}", idUsuario);
		ResponseEntity<Boolean> result = null;
		boolean deleteOk = false;

		try {
			Optional<Usuarios> usuario = usuariosDao.findUsuarioId(idUsuario);
			if (usuario.isPresent()) {
				usuariosDao.deleteById(idUsuario);
				deleteOk = true;
			} else {
				LOGGER.error("ERROR DELETE USUARIO, USUARIO NO ENCONTRADO - {}", idUsuario);
				result = new ResponseEntity<Boolean>(deleteOk, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error("ERROR DELETE USUARIO - {}", e.getLocalizedMessage());
			result = new ResponseEntity<Boolean>(deleteOk, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@Override
	public ResponseEntity<Optional<Usuarios>> findUsuarioFullName(String nombre, String aPaterno, String aMaterno) {

		LOGGER.info("STARTING FIND USUARIO");
		ResponseEntity<Optional<Usuarios>> result = null;
		Optional<Usuarios> optUsuario = Optional.empty();

		try {
			optUsuario = usuariosDao.findUsuarioFullName(nombre.toUpperCase().trim(), aPaterno.toUpperCase().trim(),
					aMaterno.toUpperCase().trim());
			LOGGER.info("END FIND USUARIO - {}", optUsuario.get().toString());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			LOGGER.error("ERROR FIND USUARIO - {}", e.getLocalizedMessage());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ERROR FIND USUARIO - {}", e.getLocalizedMessage());
			result = new ResponseEntity<Optional<Usuarios>>(optUsuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

}
