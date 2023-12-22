package com.rivera.colegio.apoderado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApoderadoDTO {
	private int id;
	private String nombre;
	private String apellidopaterno;
	private String apellidomaterno;
	private String dni;
	private String telefono;
	private String ocupacion;
	private String direccion;
	

}

