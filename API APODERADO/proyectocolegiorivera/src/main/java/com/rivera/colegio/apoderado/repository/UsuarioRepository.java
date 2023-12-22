package com.rivera.colegio.apoderado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivera.colegio.apoderado.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByEmailContaining(String texto);
	Usuario findByEmail(String email);
    
}
