package edu.usuarios.data.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.usuarios.data.entity.Usuarios;

@Repository
public interface UsuariosDao extends JpaRepository<Usuarios, Long> {
	
	@Query("SELECT c FROM Usuarios c WHERE c.nombre = :nombre AND c.aPaterno = :aPaterno AND c.aMaterno = :aMaterno")
	public Optional<Usuarios> findUsuarioFullName(@Param("nombre") String nombre,@Param("aPaterno") String aPaterno, @Param("aMaterno") String aMaterno);
	
	@Query("SELECT c FROM Usuarios c WHERE c.idUsuario = :idUsuario")
	public Optional<Usuarios> findUsuarioId(@Param("idUsuario") Long idUsuario);

}
