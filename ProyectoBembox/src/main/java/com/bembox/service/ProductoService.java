package com.bembox.service;

import com.bembox.entity.Producto;

import java.util.List;

public interface ProductoService {

    Producto guardarProducto(Producto producto);

    List<Producto> listarTodosProducto();

    boolean eliminarProductoById(Long id);

    Producto buscarProductoById(Long id);
	 
	 List<Producto> listarProductosRecomendados();
	 
    List<Producto> buscarPorNombre(String nombre);

}
