package com.bembox.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.Estado;
import com.bembox.repository.EstadoRepository;
import com.bembox.service.EstadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	EstadoRepository estadoRepository;

	@Override
	public Estado buscarPorNombre(String nombre) {
	    return estadoRepository.findByNombre(nombre)
	        .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
	}
}