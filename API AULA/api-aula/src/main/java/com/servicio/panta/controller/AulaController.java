package com.servicio.panta.controller;

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

import com.servicio.panta.converter.AulaConverter;
import com.servicio.panta.dto.AulaDTO;
import com.servicio.panta.entity.Aula;
import com.servicio.panta.service.AulaService;
import com.servicio.panta.utils.WrapperResponse;

@RestController
@RequestMapping("/aula")
public class AulaController {
	@Autowired
	private AulaService service;
	
	@Autowired
	private AulaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<AulaDTO>> findAll(
			@RequestParam(value = "numero", required = false, defaultValue = "") String numero,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page =PageRequest.of(pageNumber,pageSize);
		List<Aula> articulos;
		if (numero == null) {
			articulos=service.findAll(page);
		}else {
			articulos=service.findByNumero(numero,page);
		}
		/*if (articulos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}*/
		List<AulaDTO> articulosDTO = converter.fromEntity(articulos);
		return new WrapperResponse(true,"Succes",articulosDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<AulaDTO>> findById(@PathVariable("id") int id){
		Aula articulo = service.findById(id);
		/*if (articulo ==null) {
			return ResponseEntity.notFound().build();
		}*/
		AulaDTO articuloDTO=converter.fromEntity(articulo);
		return new WrapperResponse<AulaDTO>(true,"Succes",articuloDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<AulaDTO> create(@RequestBody AulaDTO articuloDTO){
		
		Aula registro=service.save(converter.fromDTO(articuloDTO));
		AulaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"Succes",registroDTO).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<AulaDTO> update(@PathVariable("id") int id,@RequestBody AulaDTO articuloDTO){
		Aula registro = service.update(converter.fromDTO(articuloDTO));
		/*if (registro==null) {
			return ResponseEntity.notFound().build();
		}*/
		AulaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"Succes",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<AulaDTO> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"Succes",null).createResponse(HttpStatus.OK);
	}
	
}
