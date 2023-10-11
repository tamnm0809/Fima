package com.fima.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fima.entity.Categories;
import com.fima.entity.Services;
import com.fima.service.CategoriesService;
import com.fima.service.ServicesService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ServicesService servicesService;

	@Autowired
	CategoriesService categoriesService;

	String message;

	@GetMapping("/services/getAllServices")
	public String page(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listService", listPage);
		System.out.println(listPage.getContent());
		return "page/servicesAdmin";
	}

	@GetMapping("/services/edit")
	public String getServicesById(Model model, @RequestParam("id") long id,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
		Optional<Services> service = servicesService.getServicesById(id);
		model.addAttribute("listService", servicesService.getAllServices());
		model.addAttribute("servicesEdit", service.orElse(new Services()));
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listService", listPage);
		return "page/servicesAdmin";
	}

	@PostMapping("/services/add")
	public String addServices(Model model, @RequestParam("name") String name, @RequestParam("prices") double prices,
			@RequestParam("descriptions") String descriptions, @RequestParam("categories") Categories categories) {
		try {
			Services service = new Services();
			service.setName(name);
			service.setPrices(prices);
			service.setDescriptions(descriptions);
			service.setCategories(categories);
			servicesService.addServices(service);
			message = "Thêm mới dịch vụ thành công!";
			model.addAttribute("message", message);
		} catch (Exception e) {
			message = "Thêm mới dịch vụ thất bại!";
			System.out.println(e);
			model.addAttribute("message", message);
		}
		return "redirect:/admin/services/getAllServices";
	}

	@GetMapping("/services/update")
	public String updateServices(Model model, @ModelAttribute("servicesDetails") Services service) {
		servicesService.updateServices(service);
		model.addAttribute("listService", servicesService.getAllServices());
		return "redirect:/admin/services/getAllServices";
	}

	@RequestMapping("/services/reset")
	public String deleteServices(Model model, @ModelAttribute("servicesDetails") Services service) {
		service = null;
		model.addAttribute("listService", servicesService.getAllServices());
		return "redirect:/admin/services/getAllServices";
	}

	@GetMapping("/services/delete/{id}")
	public String deleteServices(Model model, @PathVariable("id") long id) {
		servicesService.deleteServices(id);
		List<Services> listServices = servicesService.getAllServices();
		model.addAttribute("listService", listServices);
		return "redirect:/admin/services/getAllServices";
	}

	// Categories
	@GetMapping("/services/getAllServices")
	public String pagecategori(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> listPage = categoriesService.getAllCategoriesPage(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listcategories", listPage);
		System.out.println(listPage.getContent());
		return "page/categorieAdmin";
	}

	@GetMapping("/services/edit")
	public String getCategoriesById(Model model, @RequestParam("id") long id,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> listPage = categoriesService.getAllCategoriesPage(pageNo);
		Optional<Categories> categ = categoriesService.getCategoriesById(id);
		model.addAttribute("listService", categoriesService.getAllCategories());
		model.addAttribute("categEdit", categoriesService.orElse(new Categories()));
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCategories", listPage);
		return "page/categorieAdmin";
	}

}
