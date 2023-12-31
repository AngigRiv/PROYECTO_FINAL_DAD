package com.mera.colegio.etapa.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter <E,D> {
	//Metodos abstractos
	public abstract D fromEntity(E entity);
	public abstract E fromDTO(D dto);
	
	//Metodos ya implementados 
	public List<D> fromEntity(List<E>entitys){
		return entitys.stream()
				.map(e -> fromEntity(e))
				.collect(Collectors.toList());
	}
	public List<E> fromDTO(List<D>dtos){
		return dtos.stream()
				.map(e -> fromDTO(e))
				.collect(Collectors.toList());
	}

}

