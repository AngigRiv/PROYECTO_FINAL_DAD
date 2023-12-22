package com.servicio.panta.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicio.panta.entity.Aula;


@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{
	List<Aula> findByNumeroContaining(String numero, Pageable page);
	Aula findByNumero(String numero);
}
