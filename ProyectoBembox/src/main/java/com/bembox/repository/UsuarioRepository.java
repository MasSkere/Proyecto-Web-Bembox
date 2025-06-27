package com.bembox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembox.entity.Usuario;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	Long countByRol_Tipo(String tipoRol);
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
