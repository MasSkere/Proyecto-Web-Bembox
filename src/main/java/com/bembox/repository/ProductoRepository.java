package com.bembox.repository;


import com.bembox.entity.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Categoria.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    /**
     * Busca productos cuyo nombre contiene el texto dado.
     */
    List<Producto> findByNombreContaining(String nombre);
}