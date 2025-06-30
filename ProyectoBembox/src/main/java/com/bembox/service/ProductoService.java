package com.bembox.service;

import java.util.List;

import com.bembox.entity.Producto;

public interface ProductoService {
	
	Producto guardarProducto(Producto producto);

	 List<Producto> listarTodosProducto();

	 boolean eliminarProductoById(Long id);
	
	 Producto buscarProductoById(Long id);
	 
	 List<Producto> listarProductosRecomendados();
	 
	 

}
