package com.bembox.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.Boleta;
import com.bembox.repository.BoletaRepository;
import com.bembox.service.BoletaService;

@Service
public class BoletaServiceImpl implements BoletaService{
	
	@Autowired
	BoletaRepository boletaRepository;

	@Override
	public Boleta guardarBoleta(Boleta boleta) {
		// TODO Auto-generated method stub
		return boletaRepository.save(boleta);
	}

	@Override
	public Boleta obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return boletaRepository.findById(id).get();
	}

	@Override
	public Boleta obtenerPorSerie(String serie) {
		
		return boletaRepository.findByNumeroSerie(serie);		
	}

	@Override
	public String generarNumeroBoleta() {
		// TODO Auto-generated method stub
		 int anioActual = java.time.Year.now().getValue();
		    String prefijo = "B" + anioActual;

		    Boleta ultimaBoleta = boletaRepository.buscarUltimaBoletaPorPrefijo(prefijo);
		    
		    int numeroCorrelativo = 1;
		    if (ultimaBoleta != null) {
		        String[] partes = ultimaBoleta.getNumeroSerie().split("-");
		        if (partes.length == 2) {
		            try {
		                numeroCorrelativo = Integer.parseInt(partes[1]) + 1;
		            } catch (NumberFormatException e) {
		                numeroCorrelativo = 1;
		            }
		        }
		    }

		    return String.format("%s-%06d", prefijo, numeroCorrelativo);
	}

	@Override
	public Boleta obtenerBoletaConDetalles(Long id) {
		// TODO Auto-generated method stub
		return boletaRepository.obtenerBoletaConDetalles(id);

	}
	

}
