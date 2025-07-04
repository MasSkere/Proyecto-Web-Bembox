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
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "tipo")
    private String tipo;

	
	public Rol(String tipo) {
		this.tipo = tipo;
	}

	public Rol() {
	}

	public Rol(Long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	

}
