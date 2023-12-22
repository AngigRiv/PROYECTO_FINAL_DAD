package com.guerrero.colegio.alumno.dto;

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
public class AlumnoDTO {
	private int id;
	private String nombre;
	private String apellidopaterno;
	private String apellidomaterno;
	private Integer edad;
	private String sexo;
	private String semestre;
	private String correo;
	private String telefono;

}

