package com.mera.colegio.etapa.converter;

import org.springframework.stereotype.Component;

import com.mera.colegio.etapa.dto.EtapaDTO;
import com.mera.colegio.etapa.entity.Etapa;


@Component
public class EtapaConverter extends AbstractConverter<Etapa,EtapaDTO>{

	//Convertir a un DTo
	@Override
	public EtapaDTO fromEntity(Etapa entity) {
		if(entity==null) return null;
		return EtapaDTO.builder() //construirlo
				.id(entity.getId())
				.alumno(entity.getAlumno())
				.year(entity.getYear())
				.semestre(entity.getSemestre())
				.fechaI(entity.getFechaI())
				.fechaF(entity.getFechaF())
				.estado(entity.getEstado())
				.descripcion(entity.getDescripcion())
				//.precio(entity.getPrecio())
				.build();
	}

	@Override
	public Etapa fromDTO(EtapaDTO dto) {
		if(dto==null) return null;
		return Etapa.builder() //construirlo
				.id(dto.getId())
				.alumno(dto.getAlumno())
				.year(dto.getYear())
				.semestre(dto.getSemestre())
				.fechaI(dto.getFechaI())
				.fechaF(dto.getFechaF())
				.estado(dto.getEstado())
				.descripcion(dto.getDescripcion())
				//.precio(dto.getPrecio())
				.build();
	}

}
