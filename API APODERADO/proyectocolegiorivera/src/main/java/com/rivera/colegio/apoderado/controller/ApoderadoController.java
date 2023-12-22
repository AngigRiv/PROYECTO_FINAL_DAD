package com.rivera.colegio.apoderado.controller;

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


import com.rivera.colegio.apoderado.converter.ApoderadoConverter;
import com.rivera.colegio.apoderado.dto.ApoderadoDTO;
import com.rivera.colegio.apoderado.entity.Apoderado;
import com.rivera.colegio.apoderado.service.ApoderadoService;
import com.rivera.colegio.apoderado.utils.WrapperResponse;


@RestController
@RequestMapping("/apoderado")
public class ApoderadoController {
	
	@Autowired
	private ApoderadoService service;
	
	@Autowired 
	private ApoderadoConverter converter;
	
	//Metodos 
	@GetMapping()
	public ResponseEntity<List<ApoderadoDTO>> findAll( // el Metodo findAll va a devolver los DTO
			//Parametros
			@RequestParam (value = "nombre",required = false, defaultValue ="") String nombre,
			@RequestParam (value = "offset",required = false, defaultValue ="0") int pageNumbre,
			@RequestParam (value = "limit",required = false, defaultValue ="5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumbre,pageSize);
		List<Apoderado> apoderados;
		if(nombre.isEmpty()) {
			apoderados=service.findAll(page);	
		}else {
			apoderados=service.finByNombre(nombre, page);
		}
		
		List<ApoderadoDTO> apoderadosDTO=converter.fromEntity(apoderados); //convirte una lista de entidades a una lista de DTO
		return new WrapperResponse(true,"success",apoderadosDTO).createResponse(HttpStatus.OK); // Devuelve la lista
	}
	
	
	@GetMapping(value="/{id}") //Notaciòn
	public ResponseEntity<WrapperResponse<ApoderadoDTO>> findById(@PathVariable("id")int id){
		Apoderado apoderado = service.findById(id);
		ApoderadoDTO apoderadoDTO=converter.fromEntity(apoderado); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse<ApoderadoDTO>(true,"success",apoderadoDTO).createResponse(HttpStatus.OK); //retorna un DTO
	}
	
	@PostMapping()
	public ResponseEntity<ApoderadoDTO> create(@RequestBody ApoderadoDTO apoderadoDTO){ //esperando un articulo DTO
		Apoderado registro=service.save(converter.fromDTO(apoderadoDTO)); // convertir de un DTO a una entidad
		ApoderadoDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED); //devolver el registro DTO
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ApoderadoDTO> update(@PathVariable("id")int id,@RequestBody ApoderadoDTO apoderadoDTO){
		Apoderado registro = service.update(converter.fromDTO(apoderadoDTO));
		ApoderadoDTO registroDTO=converter.fromEntity(registro); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse(true,"success",registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<ApoderadoDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}