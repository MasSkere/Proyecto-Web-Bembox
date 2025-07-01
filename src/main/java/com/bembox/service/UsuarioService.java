package com.bembox.service;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)

import com.bembox.entity.Usuario;



public interface UsuarioService {
	
<<<<<<< HEAD
	public Usuario guardarUsuario(Usuario usuario);
   
	public Usuario ActualizarUsuario(Usuario usuario);
	
	public List<Usuario> listarTodosUsuario();
	
	public boolean login(Usuario usuario);
	
	public Usuario buscarByUsuario(String username);
	
	public Long ContarTipoRolUsuario(Long usuario);

=======
    Usuario guardarUsuario(Usuario usuario);

    List<Usuario> listarTodosUsuario();

    Optional<Usuario> findByNombreUsuario(String username);

    Long contarUsuariosPorRol(String tipoRol);

	long contarUsuarios();
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
}
