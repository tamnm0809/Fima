package com.fima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.expression.spel.ast.OpAnd;

import com.fima.entity.Categories;

public interface CategoriesService{
	public List<Categories> getAllCategories();

	public Page<Categories> getAllPageCategories(Integer pageNo);
	
	public Categories addCategories(Categories cate);
	
	public Categories updateCategories(Categories cate);
	
	public void deleteCategories(long id);
	
	public Optional<Categories> getCategoriesById(long id);

}	
