package com.bembox.entity;

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
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	
	
    
    
    

}