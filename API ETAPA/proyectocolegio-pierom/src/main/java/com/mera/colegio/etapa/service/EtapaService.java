package com.mera.colegio.etapa.service;

import org.springframework.data.domain.Pageable;

import com.mera.colegio.etapa.entity.Etapa;

import java.util.List;

public interface EtapaService {
	//Metodos
	public List<Etapa> findAll(Pageable page);
	List<Etapa> finByAlumno(String alumno, Pageable page); //Busqueda
	public Etapa findById (int id);
	public Etapa save (Etapa etapa);
	public Etapa update (Etapa etapa);
	public void delete (int id);
	

}

