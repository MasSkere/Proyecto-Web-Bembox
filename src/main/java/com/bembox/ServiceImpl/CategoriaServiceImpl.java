// src/main/java/com/bembox/service/impl/CategoriaServiceImpl.java
package com.bembox.ServiceImpl;

import com.bembox.entity.Categoria;
import com.bembox.repository.CategoriaRepository;
import com.bembox.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repo;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Categoria> listarTodas() {
        return repo.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return repo.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
