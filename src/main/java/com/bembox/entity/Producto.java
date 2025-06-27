package com.bembox.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoID")
    private Integer productoId;

    @Column(name = "Nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaId")
    private Categoria categoria;

    @Column(name = "ImagenURL", length = 255)
    private String imagenURL;
}
