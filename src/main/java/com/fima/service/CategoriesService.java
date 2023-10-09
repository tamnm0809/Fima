package com.fima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fima.entity.Categories;


public interface CategoriesService{
	
	public List<String> getAllCategoriesByName();
	
	public List<Categories> getAllCategories();
	
	public Page<Categories> getAllCategoriesPage(Integer PageNo);

	public Categories addCategories(Categories categories);
	
	public void updateCategories(Categories categories);
	
	public void deleteCategories(long id);
	
	public Optional<Categories> getCategoriesById(long id);
}
