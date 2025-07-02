package com.bembox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bembox.service.ProductoService;
import com.bembox.service.ClienteService;
import com.bembox.service.PedidoService;
import com.bembox.entity.Pedido;
import com.bembox.service.CategoriaService;
import com.bembox.service.BoletaService;
import com.bembox.service.UsuarioService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final PedidoService pedidoService;
    private final CategoriaService categoriaService;
	private final BoletaService boletaService;
	private final UsuarioService UsuarioService;

    @Autowired
    public AdminController(UsuarioService usuarioService,
    					   BoletaService boletaService, 
    					   ProductoService productoService,
                           ClienteService clienteService,
                           PedidoService pedidoService,
                           CategoriaService categoriaService) {
        this.UsuarioService = usuarioService;
    	this.boletaService = boletaService;
    	this.productoService = productoService;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
        this.categoriaService = categoriaService;
    }
    
    

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalProductos", productoService.listarTodosProducto().size());
        model.addAttribute("totalClientes",  clienteService.listarTodosCliente().size());
        model.addAttribute("totalPedidos",   pedidoService.listarTodosPedidos().size());
        model.addAttribute("totalCategorias",categoriaService.listarTodas().size());
        
        long totalUsuarios = UsuarioService.contarUsuarios();
        System.out.println("Total usuarios = " + totalUsuarios);
        model.addAttribute("totalUsuarios", totalUsuarios);
   
        // **Nuevo atributo totalBoletas**
        long totalBoletas = boletaService.contarBoletas();
        model.addAttribute("totalBoletas", totalBoletas);
        
    

        // 5 últimos pedidos
        List<Pedido> ultimosPedidos = pedidoService.obtenerUltimosPedidos(5);
        model.addAttribute("ultimosPedidos", ultimosPedidos);
        
        // Boletas por mes
        Map<String, Long> boletasPorMes = boletaService.contarBoletasPorMes();  // CORRECTO
        model.addAttribute("boletasLabels", boletasPorMes.keySet());
        model.addAttribute("boletasData",    boletasPorMes.values());

        return "admin/dashboard";
    }
    
}
