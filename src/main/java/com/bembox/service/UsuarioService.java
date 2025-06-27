package com.bembox.service;

import java.util.List;

import com.bembox.entity.Usuario;



public interface UsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario);
   
	public Usuario ActualizarUsuario(Usuario usuario);
	
	public List<Usuario> listarTodosUsuario();
	
	public boolean login(Usuario usuario);
	
	public Usuario buscarByUsuario(String username);
	
	public Long ContarTipoRolUsuario(Long usuario);

}
