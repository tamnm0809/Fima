package com.fima.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fima.entity.Categories;
import com.fima.repository.CategoriesRepository;
import com.fima.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService{


@Autowired
CategoriesRepository categoriesRepository;

@Override
public List<Categories> searchCategoriesByName(String name) {
    List<Categories> categoriesList = categoriesRepository.findAllByNameContainingIgnoreCase(name);
    return categoriesList;
}

@Override
public List<Categories> getAllCategories() {
	return categoriesRepository.findAll();
}

@Override
public Page<Categories> getAllCategoriesPage(Integer pageNo) {
	Pageable pageable = PageRequest.of(pageNo - 1, 5);
	return categoriesRepository.findAll(pageable);
}

@Override
public Categories addCategories(Categories categories) {
	if (categories != null) {
		return categoriesRepository.save(categories);
	}
	return null;
}

@Override
public void updateCategories(Categories categories) {
	categoriesRepository.save(categories);
}

@Override
public void deleteCategories(Long id) {
	categoriesRepository.deleteById(id);
}

@Override
public Optional<Categories> getCategoriesById(Long id) {
	return categoriesRepository.findById(id);
}
}