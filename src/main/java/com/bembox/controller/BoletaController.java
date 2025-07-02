package com.bembox.controller;

import com.bembox.entity.Boleta;
import com.bembox.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepo;

    /** Listar todas las boletas */
    @GetMapping
    public List<Boleta> listarTodas() {
        return boletaRepo.findAll();
    }

    /** Obtener boleta por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Boleta> obtenerPorId(@PathVariable Long id) {
        Optional<Boleta> boleta = boletaRepo.findById(id);
        return boleta
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** Buscar boletas por número de serie (contiene) */
    @GetMapping("/buscar")
    public List<Boleta> buscarPorSerie(@RequestParam("serie") String serie) {
        return boletaRepo.findByNumeroSerieContainingIgnoreCase(serie);
    }

    /** Crear nueva boleta */
    @PostMapping
    public ResponseEntity<Boleta> crear(@RequestBody Boleta boleta) {
        Boleta guardada = boletaRepo.save(boleta);
        return ResponseEntity
                .created(null)  // aquí podrías generar URI
                .body(guardada);
    }

    /** Actualizar boleta existente */
    @PutMapping("/{id}")
    public ResponseEntity<Boleta> actualizar(
            @PathVariable Long id,
            @RequestBody Boleta datos) {

        return boletaRepo.findById(id)
            .map(boleta -> {
                boleta.setPedido(datos.getPedido());
                boleta.setFechaEmision(datos.getFechaEmision());
                boleta.setNumeroSerie(datos.getNumeroSerie());
                boleta.setMontoTotal(datos.getMontoTotal());
                boleta.setDetalles(datos.getDetalles());
                Boleta actualizada = boletaRepo.save(boleta);
                return ResponseEntity.ok(actualizada);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** Eliminar boleta */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (boletaRepo.existsById(id)) {
            boletaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
