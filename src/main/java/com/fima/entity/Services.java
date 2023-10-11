package com.fima.entity;

import java.io.Serializable;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services implements Serializable {

	

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_services")
	private Long id_services;
	
	@Column(name="id_categories")
	private Long id_categories;
	
	@Column(name="name_service")
	private String name;
	
	@Column(name="description")
	private String descriptions;
	
	@Column(name="price")
	private Double prices;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_categories" ,insertable = false,updatable = false)
	private Categories categories;

}
