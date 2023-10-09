package com.fima.entity;

import java.io.Serializable;

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

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_services")
	private Long id_services;
	
	@Column(name="name", columnDefinition = "NVARCHAR(100)")
	private String name;
	
	@Column(name="prices")
	private Double prices;
	
	@Column(name="descriptions", columnDefinition = "NVARCHAR(500)")
	private String descriptions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_categories")
	private Categories categories;

}
