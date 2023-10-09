package com.fima.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categories")
	private Long id_categories;
	
	@Column(name="name", columnDefinition = "NVARCHAR(50)")
	private String name;
	
	@Column(name="descriptions", columnDefinition = "NVARCHAR(500)")
	private String descriptions;
	
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private Set<Services> services;
	
}
