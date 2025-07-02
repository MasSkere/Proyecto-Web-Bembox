package com.bembox.service;

import java.util.List;
import java.util.Optional;

import com.bembox.entity.Usuario;



public interface UsuarioService {
	
    Usuario guardarUsuario(Usuario usuario);

    List<Usuario> listarTodosUsuario();

    Optional<Usuario> findByNombreUsuario(String username);

    Long contarUsuariosPorRol(String tipoRol);
    
    Usuario buscarPorNombreUsuario(String nombreUsuario);
}
