package com.bembox.ServiceImpl;

import com.bembox.entity.Boleta;
import com.bembox.repository.BoletaRepository;
import com.bembox.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    
    @Override
    public long contarBoletas() {
        return boletaRepository.count();
    }
    
    @Override
    public Map<String, Long> contarBoletasPorMes() {
        // LLAMA AL MÉTODO SOBRE LA INSTANCIA boletaRepo, ¡NO SOBRE LA CLASE!
        List<Object[]> rows = boletaRepository.contarBoletasPorMes();  // CORRECTO
        if (rows == null) {
            rows = Collections.emptyList();
        }
        Map<String, Long> result = new LinkedHashMap<>();
        for (Object[] row : rows) {
            String mes = (String) row[0];
            Long cnt   = ((Number) row[1]).longValue();
            result.put(mes, cnt);
        }
        return result;
    }

}
