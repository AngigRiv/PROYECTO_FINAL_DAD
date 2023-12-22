package com.guerrero.colegio.alumno.controller;

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

import com.guerrero.colegio.alumno.converter.AlumnoConverter;
import com.guerrero.colegio.alumno.dto.AlumnoDTO;
import com.guerrero.colegio.alumno.entity.Alumno;
import com.guerrero.colegio.alumno.service.AlumnoService;
import com.guerrero.colegio.alumno.utils.WrapperResponse;


@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@Autowired 
	private AlumnoConverter converter;
	
	//Metodos 
	@GetMapping()
	public ResponseEntity<List<AlumnoDTO>> findAll( // el Metodo findAll va a devolver los DTO
			//Parametros
			@RequestParam (value = "nombre",required = false, defaultValue ="") String nombre,
			@RequestParam (value = "offset",required = false, defaultValue ="0") int pageNumbre,
			@RequestParam (value = "limit",required = false, defaultValue ="5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumbre,pageSize);
		List<Alumno> alumnos;
		if(nombre==null) {
			alumnos=service.findAll(page);	
		}else {
			alumnos=service.finByNombre(nombre, page);
		}
		
		List<AlumnoDTO> alumnosDTO=converter.fromEntity(alumnos); //convirte una lista de entidades a una lista de DTO
		return new WrapperResponse(true,"success",alumnosDTO).createResponse(HttpStatus.OK); // Devuelve la lista
	}
	
	@GetMapping(value="/{id}") //Notaciòn
	public ResponseEntity<WrapperResponse<AlumnoDTO>> findById(@PathVariable("id")int id){
		Alumno alumno = service.findById(id);
		AlumnoDTO alumnoDTO=converter.fromEntity(alumno); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse<AlumnoDTO>(true,"success",alumnoDTO).createResponse(HttpStatus.OK); //retorna un DTO
	}
	
	@PostMapping()
	public ResponseEntity<AlumnoDTO> create(@RequestBody AlumnoDTO alumnoDTO){ //esperando un articulo DTO
		Alumno registro=service.save(converter.fromDTO(alumnoDTO)); // convertir de un DTO a una entidad
		AlumnoDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED); //devolver el registro DTO
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<AlumnoDTO> update(@PathVariable("id")int id,@RequestBody AlumnoDTO alumnoDTO){
		Alumno registro = service.update(converter.fromDTO(alumnoDTO));
		AlumnoDTO registroDTO=converter.fromEntity(registro); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse(true,"success",registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<AlumnoDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
