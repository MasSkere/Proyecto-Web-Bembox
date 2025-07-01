// src/main/java/com/bembox/service/CategoriaService.java
package com.bembox.service;

import com.bembox.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Categoria buscarPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}
