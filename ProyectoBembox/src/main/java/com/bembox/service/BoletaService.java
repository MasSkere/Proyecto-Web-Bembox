package com.bembox.service;

import com.bembox.entity.Boleta;
import java.util.List;
import java.util.Optional;

public interface BoletaService {
    // Listar todas las boletas
    List<Boleta> listarTodasBoletas();

    // Obtener una boleta por ID
    Optional<Boleta> obtenerBoletaPorId(Long id);

    // Guardar o actualizar una boleta
    Boleta guardarBoleta(Boleta boleta);

    // Eliminar una boleta por ID
    void eliminarBoleta(Long id);

    // Buscar boletas por número de serie
    List<Boleta> buscarPorNumeroSerie(String numeroSerie);
}
