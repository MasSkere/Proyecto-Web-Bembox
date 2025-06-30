package com.bembox.service;

import java.util.List;
import java.util.Optional;

import com.bembox.entity.Pedido;

public interface PedidoService {
	
	Pedido guardarPedido(Pedido pedido);
	
	Pedido obtenerPorId(Long id);
	
	List<Pedido> listarPorClienteId(Long clienteId);
	


}
