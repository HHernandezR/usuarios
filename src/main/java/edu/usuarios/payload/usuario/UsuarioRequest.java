package edu.usuarios.payload.usuario;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String aPaterno;

	@NotEmpty
	private String aMaterno;

}
