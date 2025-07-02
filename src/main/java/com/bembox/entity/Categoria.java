package com.bembox.entity;

<<<<<<< HEAD
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
@Table(name="categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoria_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;

	public Categoria(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Categoria() {
	}
	
	
	
	

}
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
