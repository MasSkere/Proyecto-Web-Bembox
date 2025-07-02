package com.bembox.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boleta_id")

    private Long Id;

    @OneToOne
    @JoinColumn(name = "pedido_id", unique = true) // 👈 clave única
    private Pedido pedido;


    @Column(name="fecha_emision")
    private LocalDateTime fecha_emision = LocalDateTime.now();


    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name="monto_total")
    private BigDecimal montoTotal;
    
    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleBoleta> detalles;
   
  
	public Boleta() {
	}
	
}
