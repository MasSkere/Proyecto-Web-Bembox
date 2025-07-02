package com.bembox.controller;

import com.bembox.entity.DatosEnvioDTO;
import com.bembox.entity.DetalleBoleta;
import com.bembox.entity.Boleta;
import com.bembox.entity.Cliente;
import com.bembox.entity.ItemCarrito;
import com.bembox.entity.Pedido;
import com.bembox.entity.Producto;
import com.bembox.entity.DetallePedido;
import com.bembox.entity.Estado;
import com.bembox.service.BoletaService;
import com.bembox.service.ClienteService;
import com.bembox.service.DetalleBoletaService;
import com.bembox.service.DistritoService;
import com.bembox.service.PedidoService;
import com.bembox.service.ProductoService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
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
import java.util.ArrayList;
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
	
	@Autowired
	BoletaService boletaService;
	
	@Autowired
	DetalleBoletaService detalleBoletaService;
	
	
	
    // 1. FORMULARIO DE ENVÍO (pedido/envio.html)
	
  //Metodo de apoyo para dar el precio al envio
    
    private BigDecimal obtenerCostoEnvio(String distrito) {
        return BigDecimal.valueOf(10); // Por ahora es tarifa plana
    }
	
	    // GET: Mostrar formulario de envío
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

       // POST: Procesar formulario de envío y pasar a pago
    @PostMapping("/envio")
    public String procesarFormularioEnvio(@ModelAttribute DatosEnvioDTO datosEnvio, HttpSession session) {
        session.setAttribute("datosEnvio", datosEnvio);
        return "redirect:/pedido/pago";
    }
    
    
  // 2. MÉTODO DE PAGO (pedido/pago.html)
    
    // GET: Mostrar vista de métodos de pago
    
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
    
    // POST: Procesar la vista pagos y que guarde los datos de envio . Redirigue a la ultiam vista de (pedido/boleta)

    @PostMapping("/pago")
    public String procesarPago(HttpSession session, Model model) {
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

        // Crear y configurar el pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDireccionEnvio(datosEnvio.getTipoEntrega().equals("tienda")
                ? "Recojo en tienda"
                : datosEnvio.getDireccion());
        pedido.setTotal(total);
        pedido.setEstado(estadoService.buscarPorNombre("Pendiente"));

        // Generar detalles del pedido
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

        // Crear la boleta
        Boleta boleta = new Boleta();
        boleta.setPedido(pedido);
        boleta.setMontoTotal(total);
        boleta.setNumeroSerie(boletaService.generarNumeroBoleta());
        boletaService.guardarBoleta(boleta);

        // Generar detalles de boleta y asociarlos manualmente
        List<DetalleBoleta> detallesBoleta = new ArrayList<>();
        for (DetallePedido d : pedido.getDetalles()) {
            DetalleBoleta db = new DetalleBoleta();
            db.setBoleta(boleta);
            db.setProducto(d.getProducto());
            db.setCantidad(d.getCantidad());
            db.setPrecioUnitario(d.getPrecioUnitario());
            detalleBoletaService.guardarDetalle(db);
            detallesBoleta.add(db);
        }
        boleta.setDetalles(detallesBoleta); // Asociación manual
        
        BigDecimal totalBoleta = detallesBoleta.stream()
                .map(DetalleBoleta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        boleta.setMontoTotal(totalBoleta);
        boletaService.guardarBoleta(boleta); // Guardamos boleta con el total real


        // Limpiar la sesión y pasar datos a la vista
        session.removeAttribute("carrito");
        session.removeAttribute("datosEnvio");
        model.addAttribute("pedido", pedido);
        model.addAttribute("boleta", boleta);

        return "pedido/boleta";
    }

    
    
    
    //Metodo para generar el pdf
    
    @GetMapping("/pdf/{boletaId}")
    public void descargarPDF(@PathVariable Long boletaId, HttpServletResponse response) throws IOException, DocumentException {
        Boleta boleta = boletaService.obtenerPorId(boletaId); // tu método de servicio
        Pedido pedido = boleta.getPedido();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleta_" + boletaId + ".pdf");

        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        // Estilo simple
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font textoNormal = new Font(Font.FontFamily.HELVETICA, 12);

        documento.add(new Paragraph("BOLETA DE COMPRA", tituloFont));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Número de Boleta: " + boleta.getNumeroSerie(), textoNormal));
        documento.add(new Paragraph("Fecha: " + boleta.getFecha_emision().toLocalDate(), textoNormal));
        documento.add(new Paragraph("Cliente: " + pedido.getCliente().getNombres() + " " + pedido.getCliente().getApellidos(), textoNormal));
        documento.add(new Paragraph("Dirección de Envío: " + pedido.getDireccionEnvio(), textoNormal));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Unitario");
        tabla.addCell("Subtotal");

        for (DetalleBoleta d : boleta.getDetalles()) {
            tabla.addCell(d.getProducto().getNombre());
            tabla.addCell(String.valueOf(d.getCantidad()));
            tabla.addCell("S/ " + d.getPrecioUnitario());
            BigDecimal subtotal = d.getPrecioUnitario().multiply(new BigDecimal(d.getCantidad()));
            tabla.addCell("S/ " + subtotal);
        }

        documento.add(tabla);
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Monto Total: S/ " + boleta.getMontoTotal(), tituloFont));
        documento.close();
    }


    // . MANEJO BÁSICO DE ERRORES
    
    @ExceptionHandler(RuntimeException.class)
    public void manejarError(RuntimeException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
    }
}    
