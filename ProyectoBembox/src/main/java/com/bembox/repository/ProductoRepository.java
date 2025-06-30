package com.bembox.repository;

import com.bembox.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    /**
     * Busca todos los productos cuyo nombre contenga (ignore case) la cadena dada.
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
