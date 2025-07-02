package com.bembox.repository;

<<<<<<< HEAD
import com.bembox.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Métodos personalizados (query methods) pueden añadirse aquí
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembox.entity.Cliente;
import com.bembox.entity.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByUsuario(Usuario usuario);

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
}
