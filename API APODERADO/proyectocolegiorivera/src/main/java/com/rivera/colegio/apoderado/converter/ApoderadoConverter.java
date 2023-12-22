package com.rivera.colegio.apoderado.converter;

import org.springframework.stereotype.Component;

import com.rivera.colegio.apoderado.dto.ApoderadoDTO;
import com.rivera.colegio.apoderado.entity.Apoderado;


@Component
public class ApoderadoConverter extends AbstractConverter<Apoderado,ApoderadoDTO>{

	//Convertir a un DTo
	@Override
    public ApoderadoDTO fromEntity(Apoderado entity) {
        if (entity == null) return null;
        return ApoderadoDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellidopaterno(entity.getApellidopaterno())
                .apellidomaterno(entity.getApellidomaterno())
                .dni(entity.getDni())
                .telefono(entity.getTelefono())
                .ocupacion(entity.getOcupacion())
                .direccion(entity.getDireccion())
                .build();
    }

    @Override
    public Apoderado fromDTO(ApoderadoDTO dto) {
        if (dto == null) return null;
        return Apoderado.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellidopaterno(dto.getApellidopaterno())
                .apellidomaterno(dto.getApellidomaterno())
                .dni(dto.getDni())
                .telefono(dto.getTelefono())
                .ocupacion(dto.getOcupacion())
                .direccion(dto.getDireccion())
                .build();
    }

}
