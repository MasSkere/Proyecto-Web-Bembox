package com.bembox.ServiceImpl;

import com.bembox.entity.Producto;
import com.bembox.repository.ProductoRepository;
import com.bembox.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
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
=======

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarTodosProducto() {
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
        return productoRepository.findAll();
    }

    @Override
<<<<<<< HEAD
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
=======
    public boolean eliminarProductoById(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

    @Override
    public Producto buscarProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
    }
}
