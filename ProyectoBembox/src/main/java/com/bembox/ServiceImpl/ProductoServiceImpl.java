package com.bembox.ServiceImpl;

import com.bembox.entity.Producto;
import com.bembox.repository.ProductoRepository;
import com.bembox.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	ProductoRepository productoRepository;

	@Override
	public Producto guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public List<Producto> listarTodosProducto() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public boolean eliminarProductoById(Long id) {
		try {
			productoRepository.deleteById(id);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public Producto buscarProductoById(Long id) {
	    return productoRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	}
	
	@Override
	public List<Producto> listarProductosRecomendados() {
	    return productoRepository.findByRecomendadoTrue();
	}


