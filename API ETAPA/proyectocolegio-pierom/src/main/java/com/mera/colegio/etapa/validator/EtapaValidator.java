package com.mera.colegio.etapa.validator;

import com.mera.colegio.etapa.entity.Etapa;
import com.mera.colegio.etapa.exceptions.ValidateServiceException;

public class EtapaValidator {
	public static void save(Etapa etapa) {
        if (etapa.getAlumno() == null || etapa.getAlumno().isEmpty()) {
            throw new ValidateServiceException("El codigo es requerido");
        }
        if (etapa.getAlumno().length()>100) {
        	throw new ValidateServiceException("El codigo es muy largo");
        }/*
        if (alumno.getPrecio()==null) {
        	throw new ValidateServiceException("El precio es requerido");
        }
        if (alumno.getPrecio()<0) {
        	throw new ValidateServiceException("El precio es incorrecto");
        }*/
    }
}

