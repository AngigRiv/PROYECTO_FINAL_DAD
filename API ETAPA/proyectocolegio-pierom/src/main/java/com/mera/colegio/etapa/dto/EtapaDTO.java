package com.mera.colegio.etapa.dto;

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
public class EtapaDTO {
	private int id;
	private String alumno;
	private Integer year;
	private String semestre;
	private String fechaI;
	private String fechaF;
	private String estado;
	private String descripcion;

}

