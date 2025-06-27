package com.bembox.controller;

import com.bembox.entity.Producto;
import com.bembox.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para operaciones CRUD y búsqueda de Productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Listar todos los productos.
     */
    @GetMapping
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    /**
     * Obtener un producto por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo producto.
     */
    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualizar un producto existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto detalles) {
        return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(detalles.getNombre());
                producto.setDescripcion(detalles.getDescripcion());
                producto.setPrecio(detalles.getPrecio());
                producto.setStock(detalles.getStock());
                producto.setCategoria(detalles.getCategoria());
                producto.setImagenURL(detalles.getImagenURL());
                Producto actualizado = productoRepository.save(producto);
                return ResponseEntity.ok(actualizado);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Eliminar un producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return productoRepository.findById(id)
            .map(producto -> {
                productoRepository.delete(producto);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Buscar productos por nombre (contiene).
     * Requiere definir en ProductoRepository el método:
     * List<Producto> findByNombreContaining(String nombre);
     */
    @GetMapping("/search")
    public List<Producto> searchByName(@RequestParam String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
}
