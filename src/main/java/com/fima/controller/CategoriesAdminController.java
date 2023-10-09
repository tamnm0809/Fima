package com.fima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fima.entity.Categories;
import com.fima.service.impl.CategoriesServiceImpl;

@Controller
@RequestMapping("/admin/categories")
public class CategoriesAdminController {
	@Autowired
	CategoriesServiceImpl categoriesService;

	@GetMapping("/getAllCategories")
	public String getAllCategories(Model model) {
		List<Categories> categorie = categoriesService.getAllCategories();
		model.addAttribute("categories", categorie);
		return "";
	}

	@RequestMapping("/delete/{id}")
	public String deleteCategories(@PathVariable("id") long id) {
		categoriesService.deleteCategories(id);
		return "";
	}
}
