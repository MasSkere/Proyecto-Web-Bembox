package com.bembox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Rol {
	
	@Id
	@Column(name="RolId")
	private Long RolId;
	
	@Column(name="Tipo")
	private String tipo;
	
	


}
