package com.fima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fima.entity.Categories;
import com.fima.service.CategoriesService;

@Controller
@RequestMapping("/admin/categories")
public class AdminCateController {
	@Autowired
	CategoriesService cateSer;
	
	@GetMapping("/getAllCate")
	public String getAllCate(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> listPage = cateSer.getAllPageCategories(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCate",cateSer.getAllCategories());
		return "page/adminCate";
	}
	@PostMapping("/add")
	public String addCate(Model model, @ModelAttribute Categories cate) {
		cateSer.addCategories(cate);
		return "redirect:/admin/categories/getAllCate";
	}
	@GetMapping("/edit")
	public String editCate(Model model,@RequestParam("id") Long id, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Categories cate=cateSer.getCategoriesById(id).get();
		Page<Categories> listPage = cateSer.getAllPageCategories(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCate",cateSer.getAllCategories());
		model.addAttribute("cateEdit",cate);
		System.out.println(cate.getDescriptions());
		return "page/adminCate";
	}
	@PostMapping("/update")
	public String updateCate(Model model, @ModelAttribute Categories cate) {	
		cateSer.updateCategories(cate);
		return "redirect:/admin/categories/getAllCate";
	}
	@GetMapping("/delete")
	public String deleteCate(Model model,@RequestParam("id") Long id) {
		cateSer.deleteCategories(id);
		return "redirect:/admin/categories/getAllCate";
	}
}
