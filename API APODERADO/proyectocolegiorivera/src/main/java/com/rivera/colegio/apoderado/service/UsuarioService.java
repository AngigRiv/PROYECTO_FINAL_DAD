package com.rivera.colegio.apoderado.service;
import java.util.List;

import com.rivera.colegio.apoderado.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public Usuario findByEmail(String email);
	public List<Usuario> findByEmailContaining(String email);
	public Usuario buscarPorEmail(String email);
	public boolean validarContraseña(String email, String password);
	public Usuario create(Usuario obj);
	public Usuario update(Usuario obj);
	public int delete(int id);
    
}
