package com.servicio.panta.validator;

import com.servicio.panta.entity.Aula;
import com.servicio.panta.exceptions.ValidateServiceException;

public class AulaValidator {
	public static void save(Aula articulo){
		if (articulo.getNumero()==null || articulo.getNumero().isEmpty()) {
			throw new ValidateServiceException("El numero es requerido ");
		}
		if (articulo.getNumero().length()>12) {
			throw new ValidateServiceException("El numero es muy largo");
		}
		if (articulo.getCapacidad()==null) {
			throw new ValidateServiceException("La capacidad es requerida ");
		}
		if (articulo.getCapacidad()<0) {
			throw new ValidateServiceException("La capacidad es incorrecta");
		}
	}
}
