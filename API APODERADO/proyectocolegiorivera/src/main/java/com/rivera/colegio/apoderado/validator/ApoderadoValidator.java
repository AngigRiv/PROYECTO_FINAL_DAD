package com.rivera.colegio.apoderado.validator;

import com.rivera.colegio.apoderado.entity.Apoderado;
import com.rivera.colegio.apoderado.exceptions.ValidateServiceException;

public class ApoderadoValidator {
	public static void save(Apoderado apoderado) {
        if (apoderado.getNombre() == null || apoderado.getNombre().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (apoderado.getNombre().length()>100) {
        	throw new ValidateServiceException("El nombre es muy largo");
        }
        
        if (apoderado.getApellidopaterno().length()>100) {
        	throw new ValidateServiceException("El Apellido Paterno es muy largo");
        }
        if (apoderado.getApellidomaterno().length()>100) {
        	throw new ValidateServiceException("El Apellido Materno es muy largo");
        }
        if (apoderado.getDni().length()>100) {
        	throw new ValidateServiceException("El Apellido Materno es muy largo");
        }
        if (apoderado.getTelefono().length()>100) {
        	throw new ValidateServiceException("El Apellido Materno es muy largo");
        }
        if (apoderado.getOcupacion().length()>100) {
        	throw new ValidateServiceException("El Apellido Materno es muy largo");
        }
        if (apoderado.getDireccion().length()>100) {
        	throw new ValidateServiceException("El Apellido Materno es muy largo");
        }
    }
}

