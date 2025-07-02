package com.bembox.service;



import com.bembox.entity.Boleta;


public interface BoletaService {
	
	Boleta guardarBoleta(Boleta boleta);
	
	Boleta obtenerPorId(Long id);
	
	Boleta obtenerPorSerie(String serie);
	
	String generarNumeroBoleta();
	
	Boleta obtenerBoletaConDetalles(Long id);

}
