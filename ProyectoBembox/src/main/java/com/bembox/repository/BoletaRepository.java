package com.bembox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bembox.entity.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long>{
	
	public Boleta findByNumeroSerie(String numeroSerie);

	
	@Query("SELECT b FROM Boleta b WHERE b.numeroSerie LIKE CONCAT(:prefijo, '%') ORDER BY b.numeroSerie DESC LIMIT 1")
	Boleta buscarUltimaBoletaPorPrefijo(@Param("prefijo") String prefijo);

	@Query("SELECT b FROM Boleta b JOIN FETCH b.detalles WHERE b.id = :id")
	Boleta obtenerBoletaConDetalles(@Param("id") Long id);


}
