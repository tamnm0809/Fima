package com.fima.service.impl;

import java.util.List;
import java.util.Optional;

import com.fima.entity.Services;
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
    public List<Categories> getAllCategory() {
        return categoriesRepository.findAll();
    }

    @Override
    public Page<Categories> getAllCategoryPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        return categoriesRepository.findAll(pageable);
    }

    @Override
    public Categories addCategory(Categories categories) {
        if (categories != null) {
            return categoriesRepository.save(categories);
        }
        return null;
    }

    @Override
    public void updateCategory(Categories categories) {
        categoriesRepository.save(categories);
    }

    @Override
    public void deleteCategory(long id) {
        categoriesRepository.deleteById(id);
    }

    @Override
    public Optional<Categories> getCategoryById(long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Page<Categories> searchCategoryByName(String keyword, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 13);
        Page<Categories> listPage = categoriesRepository.findAllByName(keyword, pageable);
        return listPage;
    }

    @Override
    public Categories findByName(String name) {
        return categoriesRepository.findByName(name);
    }

    @Override
    public Categories findByDescriptions(String descriptions) {
        return categoriesRepository.findByDescriptions(descriptions);
    }
}
