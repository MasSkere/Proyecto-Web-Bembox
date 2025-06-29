package com.bembox.ServiceImpl;

import com.bembox.entity.Pedido;
import com.bembox.repository.PedidoRepository;
import com.bembox.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<Pedido> buscarPorClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}
