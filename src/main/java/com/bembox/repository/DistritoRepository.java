package com.bembox.repository;


import com.bembox.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    // Métodos personalizados (query methods) pueden añadirse aquí
}
