package com.bembox.repository;

import com.bembox.entity.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    List<Boleta> findByNumeroSerieContainingIgnoreCase(String serie);
}
