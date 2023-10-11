package com.fima.service.impl;

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
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Override
	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}
	
	@Override
	public Categories addCategories(Categories cate) {
		if (cate != null) {
			return categoriesRepository.save(cate);
		}
		return null;
	}

	@Override
	public Categories updateCategories( Categories cate) {
		
		return categoriesRepository.save(cate);
	}
	
	@Override
	public void deleteCategories(long id) {
		categoriesRepository.deleteById(id);
	}

	@Override
	public Optional<Categories> getCategoriesById(long id) {
		return categoriesRepository.findById(id);
	}

	@Override
	public Page<Categories> getAllPageCategories(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		return categoriesRepository.findAll(pageable);
	}
}
