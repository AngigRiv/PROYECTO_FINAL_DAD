package com.guerrero.colegio.alumno.validator;

import com.guerrero.colegio.alumno.entity.Alumno;
import com.guerrero.colegio.alumno.exceptions.ValidateServiceException;

public class AlumnoValidator {
	public static void save(Alumno alumno) {
        if (alumno.getNombre() == null || alumno.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (alumno.getNombre().length()>100) {
        	throw new ValidateServiceException("El nombre es muy largo");
        }/*
        if (alumno.getPrecio()==null) {
        	throw new ValidateServiceException("El precio es requerido");
        }
        if (alumno.getPrecio()<0) {
        	throw new ValidateServiceException("El precio es incorrecto");
        }*/
    }
}

