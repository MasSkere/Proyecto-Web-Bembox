package com.bembox.controller;

import com.bembox.entity.DatosEnvioDTO;
import com.bembox.entity.Cliente;
import com.bembox.entity.ItemCarrito;
import com.bembox.entity.Pedido;
import com.bembox.entity.Producto;
import com.bembox.entity.DetallePedido;
import com.bembox.entity.Estado;
import com.bembox.service.ClienteService;
import com.bembox.service.DistritoService;
import com.bembox.service.PedidoService;
import com.bembox.service.ProductoService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.bembox.service.EstadoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	EstadoService estadoService;
	
	@Autowired
	DistritoService distritoService;
	

    //  Formulario de envío
    @GetMapping("/envio")
    public String mostrarFormularioEnvio(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        DatosEnvioDTO datosEnvio = new DatosEnvioDTO();

        if (usuarioId != null) {
            Cliente cliente = clienteService.obtenerPorUsuarioId(usuarioId);
            datosEnvio.setNombres(cliente.getNombres());
            datosEnvio.setApellidos(cliente.getApellidos());
            datosEnvio.setTelefono(cliente.getTelefono());
        }

        // Calcular y enviar el subtotal al modelo
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        BigDecimal subtotal = carrito != null
                ? carrito.stream()
                         .map(ItemCarrito::getSubtotal)
                         .reduce(BigDecimal.ZERO, BigDecimal::add)
                : BigDecimal.ZERO;
        model.addAttribute("subtotal", subtotal);

        // Cargar distritos (si ya tienes distritoService)
        model.addAttribute("distritos", distritoService.listarTodosDistrito());

        model.addAttribute("datosEnvio", datosEnvio);
        

        return "pedido/envio";
    }

    

    // POST: Guardar datos de envío
    @PostMapping("/envio")
    public String procesarFormularioEnvio(@ModelAttribute DatosEnvioDTO datosEnvio, HttpSession session) {
        session.setAttribute("datosEnvio", datosEnvio);
        return "redirect:/pedido/pago";
    }
    
    @GetMapping("/pago")
    public String mostrarVistaPago(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        DatosEnvioDTO datosEnvio = (DatosEnvioDTO) session.getAttribute("datosEnvio");

        if (carrito == null || datosEnvio == null) {
            return "redirect:/pedido/envio";
        }

        BigDecimal subtotal = carrito.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal costoEnvio = "domicilio".equalsIgnoreCase(datosEnvio.getTipoEntrega()) 
            ? BigDecimal.valueOf(10) 
            : BigDecimal.ZERO;

        BigDecimal total = subtotal.add(costoEnvio);

        model.addAttribute("carrito", carrito);
        model.addAttribute("datosEnvio", datosEnvio);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("costoEnvio", costoEnvio);
        model.addAttribute("total", total);

        return "pedido/pago";
    }

  

    // POST: Finalizar pedido
    @PostMapping("/finalizar")
    public String finalizarPedido(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        DatosEnvioDTO datosEnvio = (DatosEnvioDTO) session.getAttribute("datosEnvio");
        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (carrito == null || carrito.isEmpty()) return "redirect:/carrito";
        if (usuarioId == null || datosEnvio == null) return "redirect:/login";

        Cliente cliente = clienteService.obtenerPorUsuarioId(usuarioId);

        BigDecimal subtotal = carrito.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal costoEnvio = datosEnvio.getTipoEntrega().equals("domicilio")
                ? obtenerCostoEnvio(datosEnvio.getDistrito())
                : BigDecimal.ZERO;
        


        BigDecimal total = subtotal.add(costoEnvio);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        if ("tienda".equalsIgnoreCase(datosEnvio.getTipoEntrega())) {
            pedido.setDireccionEnvio("Recojo en tienda"); // 🏪 Texto simbólico para evitar null y cumplir con DB
        } else {
            pedido.setDireccionEnvio(datosEnvio.getDireccion()); // 📦 Dirección real si es envío a domicilio
        }
        pedido.setTotal(total);
        Estado estado = estadoService.buscarPorNombre("Pendiente");
        pedido.setEstado(estado);

        List<DetallePedido> detalles = carrito.stream().map(item -> {
            Producto producto = productoService.buscarProductoById(item.getProducto().getId());
            producto.setStock(producto.getStock() - item.getCantidad());
            productoService.guardarProducto(producto);

            DetallePedido d = new DetallePedido();
            d.setProducto(producto);
            d.setCantidad(item.getCantidad());
            d.setPrecioUnitario(item.getProducto().getPrecio());
            d.setPedido(pedido);
            return d;
        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);
        pedidoService.guardarPedido(pedido);
        session.removeAttribute("carrito");
        session.removeAttribute("datosEnvio");
        model.addAttribute("pedido", pedido);
        return "pedido/exito";
    }

    private BigDecimal obtenerCostoEnvio(String distrito) {
        return BigDecimal.valueOf(10); // Por ahora es tarifa plana
    }

    
    @GetMapping("/pdf/{pedidoId}")
    public void descargarPDF(@PathVariable Long pedidoId, HttpServletResponse response) throws IOException, DocumentException {
        Pedido pedido = pedidoService.obtenerPorId(pedidoId);
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=pedido_" + pedidoId + ".pdf");

        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        documento.add(new Paragraph("Resumen del Pedido"));
        documento.add(new Paragraph("Cliente: " + pedido.getCliente().getNombres() + " " + pedido.getCliente().getApellidos()));
        documento.add(new Paragraph("Dirección de Envío: " + pedido.getDireccionEnvio()));
        documento.add(new Paragraph("Total: S/ " + pedido.getTotal()));
        documento.add(new Paragraph("Estado: " + pedido.getEstado().getNombre()));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Unitario");

        for (DetallePedido d : pedido.getDetalles()) {
            tabla.addCell(d.getProducto().getNombre());
            tabla.addCell(String.valueOf(d.getCantidad()));
            tabla.addCell("S/ " + d.getPrecioUnitario());
        }

        documento.add(tabla);
        documento.close();
    }
    
    @ExceptionHandler(RuntimeException.class)
    public void manejarError(RuntimeException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
    }
}    
