package com.guerrero.colegio.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guerrero.colegio.alumno.entity.Alumno;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	List<Alumno> findByNombreContaining(String nombre, org.springframework.data.domain.Pageable page); // Metodo para buscar por el mombre
	Alumno findByNombre(String nombre);
}