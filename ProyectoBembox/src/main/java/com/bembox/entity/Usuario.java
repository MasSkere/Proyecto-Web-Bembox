package com.bembox.entity;

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
@Table(name = "Usuarios")
public class Usuario {
	
	@Id
	@Column(name = "UsuarioID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UsuarioID;
	
	@Column(name = "NombreUsuario")
	private String nombreUsuario;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Clave")
	private String clave;
	
	@ManyToOne
	@JoinColumn(name= "RolId")
	private Rol rol;

	public Usuario() {
	
	}

	public Usuario(String nombreUsuario, String email, String clave, Rol rol) {
		
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.clave = clave;
		this.rol = rol;
	}
	
	

	
	
	
}




