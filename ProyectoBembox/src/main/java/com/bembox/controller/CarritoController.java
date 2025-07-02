package com.bembox.controller;

import com.bembox.entity.ItemCarrito;
import com.bembox.entity.Producto;
import com.bembox.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String verCarrito(Model model, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);
        BigDecimal subtotal = calcularSubtotal(carrito);

        model.addAttribute("carrito", carrito);
        model.addAttribute("subtotal", subtotal);
        return "carrito/carrito"; // vista del carrito
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long productoId, HttpSession session) {
        Producto producto = productoService.buscarProductoById(productoId);
        if (producto != null) {
            List<ItemCarrito> carrito = obtenerCarrito(session);
            boolean encontrado = false;
            for (ItemCarrito item : carrito) {
                if (item.getProducto().getId().equals(productoId)) {
                    item.setCantidad(item.getCantidad() + 1);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                carrito.add(new ItemCarrito(producto, 1));
            }
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/?agregado=true";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam Long productoId, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);
        carrito.removeIf(item -> item.getProducto().getId().equals(productoId));
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/actualizar-cantidad")
    @ResponseBody
    public void actualizarCantidad(@RequestParam Long productoId, @RequestParam int cantidad, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);
        for (ItemCarrito item : carrito) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(Math.max(1, cantidad));
                break;
            }
        }
        session.setAttribute("carrito", carrito);
        // No devuelve vista ni redirige, porque es llamado por JavaScript
    }


    @GetMapping("/limpiar")
    public String limpiarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/carrito";
    }

    private List<ItemCarrito> obtenerCarrito(HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    private BigDecimal calcularSubtotal(List<ItemCarrito> carrito) {
        return carrito.stream()
                .map(item -> item.getProducto().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    @GetMapping("/verificarLoginAntesDePedido")
    public String verificarLoginAntesDePedido(HttpSession session) {
        session.setAttribute("desdeVenta", true); // 💬 Marcar que viene del flujo de venta
        return "redirect:/login"; // 🔁 Redirigir al login
    }
    
    
}        
