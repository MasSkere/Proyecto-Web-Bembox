package com.bembox.controller;

import com.bembox.entity.Producto;
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


