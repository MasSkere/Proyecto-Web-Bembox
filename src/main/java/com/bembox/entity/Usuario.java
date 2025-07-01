package com.bembox.entity;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;

>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
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
<<<<<<< HEAD
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
=======
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)

	public Usuario() {
	
	}

<<<<<<< HEAD
	public Usuario(String nombreUsuario, String email, String clave, Rol rol) {
		
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.clave = clave;
=======
	public Usuario(String nombreUsuario, String email, String password, Rol rol) {
		
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
		this.rol = rol;
	}
	
	
<<<<<<< HEAD

	
	
	
=======
>>>>>>> ccfc68d (Init Proyecto-Web-Bembox en feature/rama_alterna)
}




