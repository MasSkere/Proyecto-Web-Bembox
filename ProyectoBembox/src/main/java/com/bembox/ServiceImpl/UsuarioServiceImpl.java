package com.bembox.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bembox.entity.Usuario;
import com.bembox.repository.UsuarioRepository;
import com.bembox.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario guardarUsuario(Usuario usuario) {
	    // Encriptar la contraseña antes de guardar
		String claveEncriptada = passwordEncoder.encode(usuario.getClave());
		usuario.setClave(claveEncriptada);
	    return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario ActualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario buscarByUsuario(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long ContarTipoRolUsuario(Long usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
