package com.bembox.controller;

import com.bembox.entity.Pedido;
import com.bembox.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepo;

    public PedidoController(PedidoRepository pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido saved = pedidoRepo.save(pedido);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        Optional<Pedido> opt = pedidoRepo.findById(id);
        return opt
            .map(p -> ResponseEntity.ok(p))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido pedidoActualizado) {

        return pedidoRepo.findById(id)
            .map(existing -> {
                // aquí puedes actualizar solo los campos que quieras
                existing.setCliente(pedidoActualizado.getCliente());
                existing.setEstado(pedidoActualizado.getEstado());
                existing.setFechaPedido(pedidoActualizado.getFechaPedido());
                existing.setTotal(pedidoActualizado.getTotal());
                existing.setDireccionEnvio(pedidoActualizado.getDireccionEnvio());
                // si incluyes detalles/boletas anidados, ojo con cascades y orfanato
                Pedido saved = pedidoRepo.save(existing);
                return ResponseEntity.ok(saved);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPedido(@PathVariable Long id) {
        if (!pedidoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // SEARCH: por ejemplo buscar por cliente
    @GetMapping("/search")
    public List<Pedido> buscarPorCliente(@RequestParam Long clienteId) {
        // asume que tienes implementado en el repository:
        // List<Pedido> findByClienteId(Long clienteId);
        return pedidoRepo.findByClienteId(clienteId);
    }
}
