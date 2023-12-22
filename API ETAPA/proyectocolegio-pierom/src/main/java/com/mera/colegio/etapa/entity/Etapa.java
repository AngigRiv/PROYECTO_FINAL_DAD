package com.mera.colegio.etapa.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="etapa")
public class Etapa {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //campo autogenerado -autoingremental
	private Integer id;
		
	@Column(unique=true,nullable = false, length = 12)	
	private String alumno;
	
	@Column(name = "year",nullable = false, length = 4)
	private Integer year;
	
	@Column(name="semestre",nullable = false, length = 12)	
	private String semestre;
	
	@Column(name="fechaI",nullable = false,length = 30)
	private String fechaI;
	
	@Column(name="fechaF",nullable = false,length = 30)
	private String fechaF;
	
	@Column(name="estado",nullable = false,length = 255)
	private String estado;
	
	@Column(name="descripcion",nullable = false,length = 255)
	private String descripcion;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt; //Agregar
	
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;//Modificar
	
	@Column(name="activo",nullable=false)
	private Boolean activo;
}

