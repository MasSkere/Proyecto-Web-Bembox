package com.bembox.service;

import com.bembox.entity.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    // Listar todos los pedidos
    List<Pedido> listarTodosPedidos();

    // Obtener un pedido por ID
    Optional<Pedido> obtenerPedidoPorId(Long id);

    // Guardar o actualizar un pedido
    Pedido guardarPedido(Pedido pedido);

    // Eliminar un pedido por ID
    void eliminarPedido(Long id);

    // Buscar pedidos por cliente
    List<Pedido> buscarPorClienteId(Long clienteId);
    
    List<Pedido> obtenerUltimosPedidos(int cantidad);

}
