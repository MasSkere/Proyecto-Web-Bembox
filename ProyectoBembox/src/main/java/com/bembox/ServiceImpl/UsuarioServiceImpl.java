package com.bembox.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bembox.entity.Usuario;
import com.bembox.repository.UsuarioRepository;
import com.bembox.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		// Encriptar la clave antes de guardar
        String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(claveEncriptada);
        return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarTodosUsuario() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findByNombreUsuario(String username) {
		return usuarioRepository.findByNombreUsuario(username);
	}

	@Override
	public Long contarUsuariosPorRol(String tipoRol) {
		return usuarioRepository.countByRol_Tipo(tipoRol);
	}

	
	
	


}
