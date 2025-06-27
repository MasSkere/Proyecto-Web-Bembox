package com.bembox.repository;

import com.bembox.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Categoria.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Métodos personalizados (query methods) pueden añadirse aquí
}