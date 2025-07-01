<<<<<<< HEAD
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
=======
// src/main/java/com/bembox/repository/CategoriaRepository.java
package com.bembox.repository;

import com.bembox.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Puedes añadir aquí métodos de consulta personalizada si los necesitas
}
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
