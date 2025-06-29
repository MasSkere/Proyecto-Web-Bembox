package com.bembox.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boleta_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "numero_serie", unique = true, nullable = false, length = 20)
    private String numeroSerie;

    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<DetalleBoleta> detalles;

    // getters y setters
}
