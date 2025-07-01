package com.bembox.repository;

<<<<<<< HEAD

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
=======
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
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
