package com.servicio.panta.converter;

import org.springframework.stereotype.Component;

import com.servicio.panta.dto.AulaDTO;
import com.servicio.panta.entity.Aula;

@Component
public class AulaConverter extends AbstractConverter<Aula,AulaDTO>{

	@Override
	public AulaDTO fromEntity(Aula entity) {
		if (entity==null)return null;
		return AulaDTO.builder()
				.id(entity.getId())
				.numero(entity.getNumero())
				.capacidad(entity.getCapacidad())
				.build();
	}

	@Override
	public Aula fromDTO(AulaDTO dto) {
		if (dto==null)return null;
		return Aula.builder()
				.id(dto.getId())
				.numero(dto.getNumero())
				.capacidad(dto.getCapacidad())
				.build();
	}

}
