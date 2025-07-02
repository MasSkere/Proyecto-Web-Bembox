package com.bembox.repository;

import com.bembox.entity.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    List<Boleta> findByNumeroSerieContainingIgnoreCase(String serie);
    
    /**
     * Devuelve lista de [ mes, cantidad ] usando un native query para MySQL.
     * Mes en formato 'YYYY-MM'.
     */
    @Query(
      value = "SELECT DATE_FORMAT(fecha_emision, '%Y-%m') AS mes, COUNT(*) AS total "
            + "FROM boletas "
            + "GROUP BY mes "
            + "ORDER BY mes",
      nativeQuery = true
    )
    List<Object[]> contarBoletasPorMes();
}
