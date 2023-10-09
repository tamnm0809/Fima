package com.fima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fima.repository.CategoriesRepository;
import com.fima.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public List<String> getAllCategoriesByName(){
		return categoriesRepository.getCategoriesByName();
	}
}
