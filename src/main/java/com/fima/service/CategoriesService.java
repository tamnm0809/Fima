package com.fima.service;

import java.util.List;
import java.util.Optional;

import com.fima.entity.Services;
import org.springframework.data.domain.Page;

import com.fima.entity.Categories;

public interface CategoriesService {

    public List<Categories> getAllCategory();

    public Page<Categories> getAllCategoryPage(Integer PageNo);

    public Categories addCategory(Categories categories);

    public void updateCategory(Categories categories);

    public void deleteCategory(long id);

    public Optional<Categories> getCategoryById(long id);

    public Page<Categories> searchCategoryByName(String keyword, Integer pageNo);

    public Categories findByName(String name);

    public Categories findByDescriptions(String descriptions);
}
