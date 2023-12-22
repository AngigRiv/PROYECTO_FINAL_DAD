package com.guerrero.colegio.alumno.service;

import org.springframework.data.domain.Pageable;
import com.guerrero.colegio.alumno.entity.Alumno;
import java.util.List;

public interface AlumnoService {
	//Metodos
	public List<Alumno> findAll(Pageable page);
	public List<Alumno> finByNombre(String nombre,Pageable page ); //Busqueda
	public Alumno findById (int id);
	public Alumno save (Alumno alumno);
	public Alumno update (Alumno alumno);
	public void delete (int id);

}

