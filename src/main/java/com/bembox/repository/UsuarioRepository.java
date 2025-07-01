package com.bembox.repository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembox.entity.Usuario;

<<<<<<< HEAD
=======

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	Long countByRol_Tipo(String tipoRol);
<<<<<<< HEAD
=======
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
}
