package edu.usuarios.payload.usuario;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateRequest implements Serializable {

	private static final long serialVersionUID = -8463451993795693229L;

	@Min(value = 1 )
	private Long idUsuario;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String aPaterno;

	@NotEmpty
	private String aMaterno;

}
