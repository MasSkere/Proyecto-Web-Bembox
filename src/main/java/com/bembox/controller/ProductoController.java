package com.bembox.controller;

import com.bembox.entity.Producto;
<<<<<<< HEAD
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
=======
import com.bembox.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /** Listar todos los productos **/
    @GetMapping
    public String listar(Model model) {
      model.addAttribute("productos", productoService.listarTodosProducto());
      return "productos";           // ahora buscará templates/productos.html
    }


    /** Formulario para crear un producto **/
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        // Apunta a templates/productos/registrarProducto.html
        return "productos/registrarProducto";
    }

    /** Guardar o actualizar **/
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    /** Formulario de edición **/
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarProductoById(id);
        if (producto == null) {
            return "redirect:/productos?error=notfound";
        }
        model.addAttribute("producto", producto);
        return "productos/registrarProducto";
    }

    /** Eliminar **/
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        boolean ok = productoService.eliminarProductoById(id);
        return "redirect:/productos" + (ok ? "" : "?error=notdeleted");
    }

    /** Buscar por nombre **/
    @GetMapping("/buscar")
    public String buscar(@RequestParam("q") String q, Model model) {
        model.addAttribute("productos", productoService.buscarPorNombre(q));
        model.addAttribute("q", q);
        return "productos/listarProducto";
    }
}


>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
