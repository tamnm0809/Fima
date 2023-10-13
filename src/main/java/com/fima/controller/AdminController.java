package com.fima.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/")
	public String index() {
		return "page/indexAdmin";
	}

	@GetMapping("/services/getAllServices")
	public String pageServices(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listService", listPage);
		return "page/servicesAdmin";
	}

	@GetMapping("/services/edit/{id}")
	public String getServicesById(Model model, @PathVariable("id") long id,
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

	@RequestMapping("/services/add")
	public String addServices(@RequestParam("name") String name, @RequestParam("prices") double prices,
			@RequestParam("descriptions") String descriptions, @RequestParam("categories") Categories categories) {
		try {
			Services service = new Services();
			service.setName(name);
			service.setPrices(prices);
			service.setDescriptions(descriptions);
			service.setCategories(categories);
			servicesService.addServices(service);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/admin/services/getAllServices";
	}

	@RequestMapping("/services/update")
	public String updateCategories(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("prices") double prices, @RequestParam("descriptions") String descriptions,
			@RequestParam("categories") Categories categories) {
		Optional<Services> service = servicesService.getServicesById(id);
		if (service.isPresent()) {
			Services services = service.get();
			services.setName(name);
			services.setPrices(prices);
			services.setDescriptions(descriptions);
			services.setCategories(categories);
			servicesService.updateServices(services);
		}
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

	@GetMapping("/categories/getAllCategories")
	public String pageCategory(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCategory", listPage);
		return "page/categoryAdmin";
	}

	@GetMapping("/categories/edit/{id}")
	public String getCategoryById(Model model, @PathVariable("id") long id,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
		Optional<Categories> category = categoriesService.getCategoryById(id);
		model.addAttribute("categoriesEdit", category.orElse(new Categories()));
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCategory", listPage);
		return "page/categoryAdmin";
	}

	@RequestMapping("/categories/add")
	public String addCategory(Model model, @RequestParam("name") String name,
			@RequestParam("descriptions") String descriptions) {
		try {
			Categories category = new Categories();
			category.setName(name);
			category.setDescriptions(descriptions);
			categoriesService.addCategory(category);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/admin/categories/getAllCategories";
	}

	@RequestMapping("/categories/update")
	public String updateCategories(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("descriptions") String descriptions) {
		Optional<Categories> optionalCategories = categoriesService.getCategoryById(id);

		if (optionalCategories.isPresent()) {
			Categories categories = optionalCategories.get();
			categories.setName(name);
			categories.setDescriptions(descriptions);
			categoriesService.updateCategory(categories);
		}
		return "redirect:/admin/categories/getAllCategories";
	}

	@RequestMapping("/categories/reset")
	public String deleteCategory(Model model, @ModelAttribute("servicesDetails") Categories categories) {
		categories = null;
		model.addAttribute("listCategory", categoriesService.getAllCategory());
		return "redirect:/admin/categories/getAllCategories";
	}

	@RequestMapping("/categories/delete/{id}")
	public String deleteCategory(Model model, @PathVariable("id") long id) {
		categoriesService.deleteCategory(id);
		List<Categories> listCategories = categoriesService.getAllCategory();
		model.addAttribute("listCategory", listCategories);
		return "redirect:/admin/categories/getAllCategories";
	}

}
