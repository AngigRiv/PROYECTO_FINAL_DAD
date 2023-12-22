package com.mera.colegio.etapa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mera.colegio.etapa.entity.Etapa;

import java.util.List;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
	List<Etapa> findByAlumnoContaining(String alumno, org.springframework.data.domain.Pageable page); // Metodo para buscar por el mombre
	Etapa findByAlumno(String alumno);
	 
}