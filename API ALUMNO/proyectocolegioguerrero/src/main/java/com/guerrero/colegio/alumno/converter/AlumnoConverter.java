package com.guerrero.colegio.alumno.converter;

import org.springframework.stereotype.Component;

import com.guerrero.colegio.alumno.dto.AlumnoDTO;
import com.guerrero.colegio.alumno.entity.Alumno;


@Component
public class AlumnoConverter extends AbstractConverter<Alumno,AlumnoDTO>{

	//Convertir a un DTo
	@Override
	public AlumnoDTO fromEntity(Alumno entity) {
		if(entity==null) return null;
		return AlumnoDTO.builder() //construirlo
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellidopaterno(entity.getApellidopaterno())
				.apellidomaterno(entity.getApellidomaterno())
				.edad(entity.getEdad())
				.sexo(entity.getSexo())
				.semestre(entity.getSemestre())
				.correo(entity.getCorreo())
				.telefono(entity.getTelefono())
				//.precio(entity.getPrecio())
				.build();
	}

	@Override
	public Alumno fromDTO(AlumnoDTO dto) {
		if(dto==null) return null;
		return Alumno.builder() //construirlo
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidopaterno(dto.getApellidopaterno())
				.apellidomaterno(dto.getApellidomaterno())
				.edad(dto.getEdad())
				.sexo(dto.getSexo())
				.semestre(dto.getSemestre())
				.correo(dto.getCorreo())
				.telefono(dto.getTelefono())
				//.precio(dto.getPrecio())
				.build();
	}

}
