package com.bembox.ServiceImpl;

import java.util.List;
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)

import com.bembox.entity.Usuario;
import com.bembox.repository.UsuarioRepository;
import com.bembox.service.UsuarioService;

<<<<<<< HEAD
=======

@Service
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
<<<<<<< HEAD
	
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
=======

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		// Encriptar la clave antes de guardar
        String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(claveEncriptada);
        return usuarioRepository.save(usuario);
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
	}

	@Override
	public List<Usuario> listarTodosUsuario() {
<<<<<<< HEAD
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
=======
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

	@Override
	public long contarUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.count();
	}

	
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
	
	


}
