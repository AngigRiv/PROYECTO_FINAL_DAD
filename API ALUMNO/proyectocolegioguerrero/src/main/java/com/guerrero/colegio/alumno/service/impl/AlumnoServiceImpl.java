package com.guerrero.colegio.alumno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guerrero.colegio.alumno.entity.Alumno;
import com.guerrero.colegio.alumno.exceptions.GeneralServiceException;
import com.guerrero.colegio.alumno.exceptions.NoDataFoundException;
import com.guerrero.colegio.alumno.exceptions.ValidateServiceException;
import com.guerrero.colegio.alumno.repository.AlumnoRepository;
import com.guerrero.colegio.alumno.service.AlumnoService;
import com.guerrero.colegio.alumno.validator.AlumnoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService {
	@Autowired
	private AlumnoRepository repository;
	
	@Override
	@Transactional(readOnly=true) // Metodo de solo lectura 
	public List<Alumno> findAll(Pageable page) {
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
	public List<Alumno> finByNombre(String nombre, Pageable page) {
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
	public Alumno findById(int id) {
		try {
			Alumno registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
	public Alumno save(Alumno alumno) {
		try {
			AlumnoValidator.save(alumno);
			if(repository.findByNombre(alumno.getNombre())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+alumno.getNombre());
			}
			alumno.setActivo(true);
			Alumno registro=repository.save(alumno);
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
	public Alumno update(Alumno alumno) {
		try {
			AlumnoValidator.save(alumno);
			Alumno registro=repository.findById(alumno.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Alumno registroD = repository.findByNombre(alumno.getNombre());
			if(registroD!=null && registroD.getId()!=alumno.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+alumno.getNombre());
			}
			registro.setNombre(alumno.getNombre());
			registro.setApellidopaterno(alumno.getApellidopaterno());
			registro.setApellidomaterno(alumno.getApellidomaterno());
			registro.setEdad(alumno.getEdad());
			registro.setSemestre(alumno.getSemestre());
			registro.setCorreo(alumno.getCorreo());
			registro.setTelefono(alumno.getTelefono());
			
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
			Alumno registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
