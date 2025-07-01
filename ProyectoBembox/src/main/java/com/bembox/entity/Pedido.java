package com.bembox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name="fecha_pedido")
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @Column(name="total")
    private BigDecimal total;

    @Column(name="direccion_envio")
    private String direccionEnvio;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;
    
    @OneToOne(mappedBy = "pedido")
    private Boleta boleta;


    
	public Pedido() {
	}
	
	
    
    
}