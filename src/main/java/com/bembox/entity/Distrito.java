package com.bembox.entity;
<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
=======

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "distritos")
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "DistritoID")
    private Integer distritoId;

    @Column(name = "Nombre", nullable = false, unique = true, length = 100)
    private String nombre;
=======
    @Column(name = "distrito_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

	public Distrito(String nombre) {
		this.nombre = nombre;
	}

	public Distrito() {
	}

	public Distrito(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	
	
    
    
    

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
}