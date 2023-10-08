package com.fima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fima.entity.Categories;
import com.fima.repository.CategoriesRepository;

@Service
public class CategoriesServiceImpl {
	@Autowired
	CategoriesRepository categoriesRepository;
	
	
	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}
	
	public void deleteCategories(Long id) {
		categoriesRepository.deleteById(id);
	}
}
