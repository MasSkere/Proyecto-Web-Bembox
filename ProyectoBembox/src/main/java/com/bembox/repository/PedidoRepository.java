package com.bembox.repository;

import org.springframework.stereotype.Repository;

import com.bembox.entity.Pedido;


import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByCliente_Id(Long id);
	
}
