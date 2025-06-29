package com.bembox.ServiceImpl;

import com.bembox.entity.Boleta;
import com.bembox.repository.BoletaRepository;
import com.bembox.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;

    @Autowired
    public BoletaServiceImpl(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    @Override
    public List<Boleta> listarTodasBoletas() {
        return boletaRepository.findAll();
    }

    @Override
    public Optional<Boleta> obtenerBoletaPorId(Long id) {
        return boletaRepository.findById(id);
    }

    @Override
    public Boleta guardarBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    @Override
    public void eliminarBoleta(Long id) {
        boletaRepository.deleteById(id);
    }

    @Override
    public List<Boleta> buscarPorNumeroSerie(String numeroSerie) {
        // Llama al método definido en el repositorio
        return boletaRepository.findByNumeroSerieContainingIgnoreCase(numeroSerie);
    }
}
