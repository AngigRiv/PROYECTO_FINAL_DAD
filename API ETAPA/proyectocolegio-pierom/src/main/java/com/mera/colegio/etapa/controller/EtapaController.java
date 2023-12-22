package com.mera.colegio.etapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mera.colegio.etapa.converter.EtapaConverter;
import com.mera.colegio.etapa.dto.EtapaDTO;
import com.mera.colegio.etapa.entity.Etapa;
import com.mera.colegio.etapa.service.EtapaService;
import com.mera.colegio.etapa.utils.WrapperResponse;


@RestController
@RequestMapping("/etapa")
public class EtapaController {
	
	@Autowired
	private EtapaService service;
	
	@Autowired 
	private EtapaConverter converter;
	
	//Metodos 
	@GetMapping()
	public ResponseEntity<List<EtapaDTO>> findAll( // el Metodo findAll va a devolver los DTO
			//Parametros
			@RequestParam (value = "alumno",required = false, defaultValue ="") String alumno,
			@RequestParam (value = "offset",required = false, defaultValue ="0") int pageAlum,
			@RequestParam (value = "limit",required = false, defaultValue ="5") int pageSize
			){
		Pageable page= PageRequest.of(pageAlum,pageSize);
		List<Etapa> etapas;
		if(alumno==null) {
			etapas=service.findAll(page);	
		}else {
			etapas=service.finByAlumno(alumno, page);
		}
		
		List<EtapaDTO> alumnosDTO=converter.fromEntity(etapas); //convirte una lista de entidades a una lista de DTO
		return new WrapperResponse(true,"success",alumnosDTO).createResponse(HttpStatus.OK); // Devuelve la lista
	}
	
	@GetMapping(value="/{id}") //Notaciòn
	public ResponseEntity<WrapperResponse<EtapaDTO>> findById(@PathVariable("id")int id){
		Etapa etapa = service.findById(id);
		EtapaDTO etapaDTO=converter.fromEntity(etapa); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse<EtapaDTO>(true,"success",etapaDTO).createResponse(HttpStatus.OK); //retorna un DTO
	}
	
	@PostMapping()
	public ResponseEntity<EtapaDTO> create(@RequestBody EtapaDTO etapaDTO){ //esperando un articulo DTO
		Etapa registro=service.save(converter.fromDTO(etapaDTO)); // convertir de un DTO a una entidad
		EtapaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED); //devolver el registro DTO
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<EtapaDTO> update(@PathVariable("id")int id,@RequestBody EtapaDTO etapaDTO){
		Etapa registro = service.update(converter.fromDTO(etapaDTO));
		EtapaDTO registroDTO=converter.fromEntity(registro); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse(true,"success",registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<EtapaDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
