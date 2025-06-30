package com.bembox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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