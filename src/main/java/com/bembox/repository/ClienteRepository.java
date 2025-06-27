package com.bembox.repository;

import com.bembox.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Métodos personalizados (query methods) pueden añadirse aquí
}
