package com.servicio.panta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicio.panta.entity.Aula;
import com.servicio.panta.exceptions.GeneralServiceException;
import com.servicio.panta.exceptions.NoDataFoundException;
import com.servicio.panta.exceptions.ValidateServiceException;
import com.servicio.panta.repository.AulaRepository;
import com.servicio.panta.service.AulaService;
import com.servicio.panta.validator.AulaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AulaServiceImpl implements AulaService{
	@Autowired
	private AulaRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Aula> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Aula> findByNumero(String numero, Pageable page) {
		try {
			return repository.findByNumeroContaining(numero,page);
		}  catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Aula findById(int id) {
		try {
			Aula registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Aula save(Aula articulo) {
		try {
			AulaValidator.save(articulo);
			if (repository.findByNumero(articulo.getNumero())!=null) {
				throw new ValidateServiceException("Ya existe un registro con los mismos datos" +articulo.getNumero());
			}
			articulo.setActivo(true);
			Aula registro=repository.save(articulo);
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Aula update(Aula articulo) {
		try {
			AulaValidator.save(articulo);
			Aula registro = repository.findById(articulo.getId()).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			Aula registroD = repository.findByNumero(articulo.getNumero());
			if (registroD!=null&& registroD.getId()!= articulo.getId()) {
				throw new ValidateServiceException("Ya existe un registro con los mismos datos" +articulo.getNumero());
			}
			registro.setNumero(articulo.getNumero());
			registro.setCapacidad(articulo.getCapacidad());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Aula registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
