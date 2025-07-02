package com.bembox.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "detalle_boleta")
public class DetalleBoleta {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "detalle_boleta_id")
	    private Long Id;

	    @ManyToOne
	    @JoinColumn(name = "boleta_id")
	    private Boleta boleta;

	    @ManyToOne
	    @JoinColumn(name = "producto_id")
	    private Producto producto;
	    
	    @Column(name="cantidad")
	    private int cantidad;
	    
	    @Column(name="precio_unitario")
	    private BigDecimal precioUnitario;
	    
	    @Transient
	    public BigDecimal getSubtotal() {
	        if (precioUnitario == null || cantidad == 0) return BigDecimal.ZERO;
	        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
	    }

}
