package com.rivera.colegio.apoderado.service;

import org.springframework.data.domain.Pageable;

import com.rivera.colegio.apoderado.entity.Apoderado;

import java.util.List;

public interface ApoderadoService {
	//Metodos
	public List<Apoderado> findAll(Pageable page);
	public List<Apoderado> finByNombre(String nombre,Pageable page ); //Busqueda
	public Apoderado findById (int id);
	public Apoderado save (Apoderado apoderado);
	public Apoderado update (Apoderado apoderado);
	public void delete (int id);
	public List<Apoderado> findAll();

}

