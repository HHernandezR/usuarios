package edu.usuarios.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "domicilio")
@Getter
@Setter
public class Domicilio implements Serializable {

	private static final long serialVersionUID = -6319237813452082873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idUsuario", referencedColumnName = "id", nullable = false)
	private Usuarios usuario;
	
	@NotEmpty
	@Size(max = 50)
	@Column(name = "colinia", nullable = false)
	private String colonia;
	
	@NotEmpty
	@Size(max = 50)
	@Column(name = "calle", nullable = false)
	private String calle;
	
	@Min(value = 0)
	@Column(name = "no_exterior", nullable = false)
	private Integer noExterior;
	
	@Min(value = 0)
	@Column(name = "no_interior", nullable = false)
	private Integer noInterior;
}
