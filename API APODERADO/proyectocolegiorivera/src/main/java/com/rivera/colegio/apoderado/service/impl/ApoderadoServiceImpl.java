package com.rivera.colegio.apoderado.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivera.colegio.apoderado.entity.Apoderado;
import com.rivera.colegio.apoderado.exceptions.GeneralServiceException;
import com.rivera.colegio.apoderado.exceptions.NoDataFoundException;
import com.rivera.colegio.apoderado.exceptions.ValidateServiceException;
import com.rivera.colegio.apoderado.repository.ApoderadoRepository;
import com.rivera.colegio.apoderado.service.ApoderadoService;
import com.rivera.colegio.apoderado.validator.ApoderadoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApoderadoServiceImpl implements ApoderadoService {
	@Autowired
	private ApoderadoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Apoderado> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly=true) // Metodo de solo lectura 
	public List<Apoderado> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Apoderado> finByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Apoderado findById(int id) {
		try {
			Apoderado registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Apoderado save(Apoderado apoderado) {
		try {
			ApoderadoValidator.save(apoderado);
			if(repository.findByNombre(apoderado.getNombre())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+apoderado.getNombre());
			}
			apoderado.setActivo(true);
			Apoderado registro=repository.save(apoderado);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Apoderado update(Apoderado apoderado) {
		try {
			ApoderadoValidator.save(apoderado);
			Apoderado registro=repository.findById(apoderado.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Apoderado registroD = repository.findByNombre(apoderado.getNombre());
			if(registroD!=null && registroD.getId()!=apoderado.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+apoderado.getNombre());
			}
			registro.setNombre(apoderado.getNombre());
			registro.setApellidopaterno(apoderado.getApellidopaterno());
			registro.setApellidomaterno(apoderado.getApellidomaterno());
			registro.setDni(apoderado.getDni());
			registro.setTelefono(apoderado.getTelefono());
			registro.setOcupacion(apoderado.getOcupacion());
			registro.setDireccion(apoderado.getDireccion());
			
			//registro.setPrecio(alumno.getPrecio());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Apoderado registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
