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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="producto_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio")
	private BigDecimal precio;
	
	
	@Column(name="stock")
	private int stock;
	
	@Column(name = "recomendado")
	private boolean recomendado;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Column(name="imagen_url")
	private String imagenUrl;

	public Producto() {
	}

	public Producto(String nombre, String descripcion, BigDecimal precio, int stock, boolean recomendado,
			Categoria categoria, String imagenUrl) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.recomendado = recomendado;
		this.categoria = categoria;
		this.imagenUrl = imagenUrl;
	}

	public Producto(String nombre, String descripcion, BigDecimal precio, int stock, boolean recomendado,
			Categoria categoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.recomendado = recomendado;
		this.categoria = categoria;
	}

	
	


	
	
	
	
	

}
