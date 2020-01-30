package edu.usuarios.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
public class Usuarios implements Serializable {
	
	private static final long serialVersionUID = -503886382183926669L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long idUsuario;
	
	@Column(name = "nombre", nullable = false)
	@Size(max = 50)
	@NotEmpty
	private String nombre;
	
	@Column(name = "apaterno", nullable = false)
	@Size(max = 50)
	@NotEmpty
	private String aPaterno;
	
	@Column(name = "amaterno", nullable = false)
	@Size(max = 50)
	@NotEmpty
	private String aMaterno;

}
