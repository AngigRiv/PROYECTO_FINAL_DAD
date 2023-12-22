package com.rivera.colegio.apoderado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivera.colegio.apoderado.entity.Apoderado;

import java.util.List;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, Integer>{
	List<Apoderado> findByNombreContaining(String nombre, org.springframework.data.domain.Pageable page); // Metodo para buscar por el mombre
	Apoderado findByNombre(String nombre);
}