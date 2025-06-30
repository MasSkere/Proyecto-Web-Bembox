package com.bembox.security;

import com.bembox.entity.Usuario;
import com.bembox.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // ✅ Inyectar el servicio correctamente
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // 💡 Obtener el nombre de usuario autenticado
        String nombreUsuario = authentication.getName();

        // ✅ Usar el servicio inyectado para buscar el usuario
        Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreUsuario);

        if (usuario != null) {
            session.setAttribute("usuarioId", usuario.getId()); // Guardamos el ID en sesión
        }

        // 🔁 Redirección condicional si viene del carrito
        Boolean desdeVenta = (Boolean) session.getAttribute("desdeVenta");
        if (desdeVenta != null && desdeVenta) {
            session.removeAttribute("desdeVenta");
            response.sendRedirect("/pedido/envio");
            return;
        }

        // 👤 Redirección según rol
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("Administrador")) {
                response.sendRedirect("/admin/home");
                return;
            } else if (role.getAuthority().equals("Cliente")) {
                response.sendRedirect("/cliente/home");
                return;
            }
        }

        // Redirección por defecto si no hay rol claro
        response.sendRedirect("/");
    }
}

