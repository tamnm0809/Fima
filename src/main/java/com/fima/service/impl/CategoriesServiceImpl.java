package com.fima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.fima.repository.CategoriesRepository;
import com.fima.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public List<String> getAllCategoriesByName(){
		return categoriesRepository.getCategoriesByName();
=======
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
>>>>>>> af5eb660ac543a4043639597f5357470a68d03a4
	}
}
