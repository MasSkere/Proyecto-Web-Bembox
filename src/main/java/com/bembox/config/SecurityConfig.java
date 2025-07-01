package com.bembox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
<<<<<<< HEAD
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF para APIs si es necesario
            .csrf(csrf -> csrf.disable())
            // Configuración de sesiones
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            // Configurar rutas públicas y privadas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index",       // página principal
                    "/css/**", "/js/**", "/images/**", // recursos estáticos
                    "/api/public/**"      // endpoints públicos si los hay
                ).permitAll()
                .anyRequest().authenticated()
            )
            // Configuración de formulario de login
            .formLogin(form -> form
                .loginPage("/login")     // tu vista /login.html
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            // Configuración de logout
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
=======
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bembox.security.LoginSuccessHandler;
import com.bembox.security.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    UserDetailsServiceImpl uds) throws Exception {
      return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
          .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
          .requestMatchers("/admin/**").hasAuthority("Administrador")
          .anyRequest().authenticated()
        )
        .formLogin(form -> form
          .loginPage("/login")
          .defaultSuccessUrl("/admin/dashboard", true)
          .permitAll()
        )
        .logout(logout -> logout
          .logoutSuccessUrl("/login?logout")
          .permitAll()
        )
        .exceptionHandling(ex -> ex
          .accessDeniedPage("/error/403")
        )
        .userDetailsService(uds)
        .build();
    }


    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
   

}
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
