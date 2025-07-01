package com.bembox.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.Pedido;
import com.bembox.repository.PedidoRepository;
import com.bembox.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	@Autowired
    PedidoRepository pedidoRepository;

	@Override
	public Pedido guardarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return pedidoRepository.save(pedido);
	}
	
	@Override
	public Pedido obtenerPorId(Long id) {
	    return pedidoRepository.findById(id).get();
	}

	@Override
	public List<Pedido> listarPorClienteId(Long clienteId) {
	    return pedidoRepository.findByCliente_Id(clienteId);
	}

}