package com.bembox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estados")
public class Estado {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_id")
    private Long Id;

    private String nombre;

	public Estado(String nombre) {
		this.nombre = nombre;
	}

	public Estado() {
	}

	public Estado(Long id, String nombre) {
		Id = id;
		this.nombre = nombre;
	}
    
	
	
    
}