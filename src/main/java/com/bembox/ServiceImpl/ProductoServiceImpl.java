package com.bembox.ServiceImpl;

import com.bembox.entity.Producto;
import com.bembox.repository.ProductoRepository;
import com.bembox.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación de Servicio para Producto.
 */
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Integer id, Producto detalles) {
        Producto producto = getProductoById(id);
        producto.setNombre(detalles.getNombre());
        producto.setDescripcion(detalles.getDescripcion());
        producto.setPrecio(detalles.getPrecio());
        producto.setStock(detalles.getStock());
        producto.setCategoria(detalles.getCategoria());
        producto.setImagenURL(detalles.getImagenURL());
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Integer id) {
        Producto producto = getProductoById(id);
        productoRepository.delete(producto);
    }

    @Override
    public List<Producto> searchProductos(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
}
