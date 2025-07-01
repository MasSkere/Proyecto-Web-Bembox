package com.bembox.entity;

<<<<<<< HEAD
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
=======
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "productos")
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
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
=======
    @Column(name = "producto_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    // Cambiado de float/double a BigDecimal
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
