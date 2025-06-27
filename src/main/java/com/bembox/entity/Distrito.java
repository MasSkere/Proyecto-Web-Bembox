package com.bembox.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DistritoID")
    private Integer distritoId;

    @Column(name = "Nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}