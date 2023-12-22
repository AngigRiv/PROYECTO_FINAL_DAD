package com.rivera.colegio.apoderado.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivera.colegio.apoderado.entity.Usuario;
import com.rivera.colegio.apoderado.repository.UsuarioRepository;
import com.rivera.colegio.apoderado.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
		   return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		try {
			return repository.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByEmailContaining(String email) {
		try {
			return repository.findByEmailContaining(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public Usuario create(Usuario obj) {
		try {
			return repository.save(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}	

	@Override
	@Transactional
	public Usuario update(Usuario obj) {
		try {
			Usuario usuarioDb=repository.findById(obj.getId()).orElse(null);
			if(usuarioDb==null) {
				return null;
			}
			usuarioDb.setEmail(obj.getEmail());
			return repository.save(usuarioDb);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public int delete(int id) {
		try {
			Usuario usuarioDb=repository.findById(id).orElse(null);
			if(usuarioDb==null) {
				return 0;
			}else {
				repository.delete(usuarioDb);
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	 @Autowired
	    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
	        this.repository = usuarioRepository;
	    }

	    @Override
	    public Usuario buscarPorEmail(String email) {
	        return repository.findByEmail(email);
	    }

	    @Override
	    public boolean validarContraseña(String email, String password) {
	        Usuario usuario = buscarPorEmail(email);
	        return usuario != null && usuario.getPassword().equals(password);
	    }
}
