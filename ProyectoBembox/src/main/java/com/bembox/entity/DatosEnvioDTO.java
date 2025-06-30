package com.bembox.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosEnvioDTO {
	private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String distrito;
    private String tipoEntrega; // "tienda" o "domicilio"

}
