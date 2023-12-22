package com.mera.colegio.etapa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mera.colegio.etapa.entity.Etapa;
import com.mera.colegio.etapa.exceptions.GeneralServiceException;
import com.mera.colegio.etapa.exceptions.NoDataFoundException;
import com.mera.colegio.etapa.exceptions.ValidateServiceException;
import com.mera.colegio.etapa.repository.EtapaRepository;
import com.mera.colegio.etapa.service.EtapaService;
import com.mera.colegio.etapa.validator.EtapaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EtapaServiceImpl implements EtapaService {
	@Autowired
	private EtapaRepository repository;
	
	@Override
	@Transactional(readOnly=true) // Metodo de solo lectura 
	public List<Etapa> findAll(Pageable page) {
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
	public List<Etapa> finByAlumno(String alumno, Pageable page) {
		try {
			return repository.findByAlumnoContaining(alumno,page);
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
	public Etapa findById(int id) {
		try {
			Etapa registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
	public Etapa save(Etapa etapa) {
		try {
			EtapaValidator.save(etapa);
			if(repository.findByAlumno(etapa.getAlumno())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el codigo"+etapa.getAlumno());
			}
			etapa.setActivo(true);
			Etapa registro=repository.save(etapa);
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
	public Etapa update(Etapa etapa) {
		try {
			EtapaValidator.save(etapa);
			Etapa registro=repository.findById(etapa.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Etapa registroD = repository.findByAlumno(etapa.getAlumno());
			if(registroD!=null && registroD.getId()!=etapa.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el mismo codigo"+etapa.getAlumno());
			}
			registro.setAlumno(etapa.getAlumno());
			registro.setYear(etapa.getYear());
			registro.setSemestre(etapa.getSemestre());
			registro.setFechaI(etapa.getFechaI());
			registro.setFechaF(etapa.getFechaF());
			registro.setEstado(etapa.getEstado());
			registro.setDescripcion(etapa.getDescripcion());
			
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
			Etapa registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
