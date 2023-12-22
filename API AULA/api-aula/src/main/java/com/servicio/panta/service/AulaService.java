package com.servicio.panta.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.servicio.panta.entity.Aula;

public interface AulaService {
	public List<Aula> findAll(Pageable page);
	public List<Aula> findByNumero(String numero, Pageable page);
	public Aula findById(int id);
	public Aula save(Aula articulo);
	public Aula update (Aula articulo);
	public void delete(int id);
}
