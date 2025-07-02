package com.bembox.service;

import com.bembox.entity.Producto;
<<<<<<< HEAD
import java.util.List;

/**
 * Interfaz de servicio para operaciones de Producto.
 */
public interface ProductoService {

    /**
     * Obtiene todos los productos.
     */
    List<Producto> getAllProductos();

    /**
     * Obtiene un producto por su ID.
     * @throws RuntimeException si no existe.
     */
    Producto getProductoById(Integer id);

    /**
     * Crea un nuevo producto.
     */
    Producto saveProducto(Producto producto);

    /**
     * Actualiza un producto existente.
     * @throws RuntimeException si no existe.
     */
    Producto updateProducto(Integer id, Producto producto);

    /**
     * Elimina un producto.
     * @throws RuntimeException si no existe.
     */
    void deleteProducto(Integer id);

    /**
     * Busca productos cuyo nombre contiene el texto dado.
     */
    List<Producto> searchProductos(String nombre);
}
=======

import java.util.List;

public interface ProductoService {

    Producto guardarProducto(Producto producto);

    List<Producto> listarTodosProducto();

    boolean eliminarProductoById(Long id);

    Producto buscarProductoById(Long id);

    /**
     * Busca productos por nombre (contiene, case-insensitive).
     */
    List<Producto> buscarPorNombre(String nombre);

}
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
