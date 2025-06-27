package com.bembox.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "Categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaId")
    private Integer categoriaId;

    @Column(name = "Nombre", nullable = false, unique = true, length = 50)
    private String nombre;
}