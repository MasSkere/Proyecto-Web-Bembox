package com.bembox.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.Distrito;
import com.bembox.repository.DistritoRepository;
import com.bembox.service.DistritoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistritoServiceImpl implements DistritoService{
	
	@Autowired
	DistritoRepository distritoRepository;

	@Override
	public List<Distrito> listarTodosDistrito() {
		
		return distritoRepository.findAll();
	}
	
	

}
