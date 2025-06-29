package com.bembox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bembox.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
}
