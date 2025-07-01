package com.bembox.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.DetalleBoleta;
import com.bembox.repository.DetalleBoletaRepository;
import com.bembox.service.DetalleBoletaService;


@Service
public class DetalleBoletaServiceImpl implements DetalleBoletaService{
	
	@Autowired
	DetalleBoletaRepository detalleBoletaRepository;

	@Override
	public DetalleBoleta guardarDetalle(DetalleBoleta detalle) {
		// TODO Auto-generated method stub
		return detalleBoletaRepository.save(detalle);
	}

}
