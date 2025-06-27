package com.bembox.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.bembox.entity.Usuario;
import com.bembox.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioService.findByNombreUsuario(username);
        if (!usuarioOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new UserDetailsImpl(usuarioOptional.get());
    }
}
