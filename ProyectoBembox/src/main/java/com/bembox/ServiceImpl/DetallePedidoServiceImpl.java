package com.bembox.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.DetallePedido;
import com.bembox.repository.DetallePedidoRepository;
import com.bembox.service.DetallePedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

	@Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedido guardarDetalle(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }
}