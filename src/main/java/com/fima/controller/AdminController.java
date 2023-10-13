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
	@GetMapping("/categories")
	public String getAllCategories(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Categories> page = categoriesService.getAllCategoriesPage(pageNo);
		model.addAttribute("listCategories", page.getContent());
		model.addAttribute("currentPageCate", pageNo);
		model.addAttribute("totalPageCate", page.getTotalPages());
		return "page/categoriesAdmin";
	}

	@PostMapping("/categories/add")
	public String addCategories(@RequestParam("nameCate") String name,
			@RequestParam("descriptionsCate") String descriptions) {
		Categories categories = new Categories();
		categories.setName(name);
		categories.setDescriptions(descriptions);
		categoriesService.addCategories(categories);
		return "redirect:/admin/categories";
	}

	@GetMapping("/categories/edit/{id}")
	public String editCategories(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Optional<Categories> categories = categoriesService.getCategoriesById(id);
		if (categories.isPresent()) {
			model.addAttribute("categoriesEdit", categories.get());
		}
		Page<Categories> page = categoriesService.getAllCategoriesPage(pageNo);
		model.addAttribute("listCategories", page.getContent());
		model.addAttribute("currentPageCate", pageNo);
		model.addAttribute("totalPageCate", page.getTotalPages());
		return "page/categoriesAdmin";
	}

	@PostMapping("/categories/update")
	public String updateCategories(@RequestParam("idCate") Long id, @RequestParam("nameCate") String name,
	        @RequestParam("descriptionsCate") String descriptions) {
	    Optional<Categories> optionalCategories = categoriesService.getCategoriesById(id);
	    
	    if (optionalCategories.isPresent()) {
	        Categories categories = optionalCategories.get();
	        categories.setName(name);
	        categories.setDescriptions(descriptions);
	        categoriesService.updateCategories(categories);
	    }  
	    return "redirect:/admin/categories";
	}
	
	@GetMapping("/categories/delete/{id}") 
	public String deleteCategories(Model model, @PathVariable("id") long id) {
		categoriesService.deleteCategories(id);
		return "redirect:/admin/categories";
	}

	@PostMapping("/categories/reset")
	public String ResetCategories() {
		return "redirect:/admin/categories";
	}


	@PostMapping("/categories/search")
	public String searchCategories(Model model, @RequestParam("searchName") String searchName, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
	    Page<Categories> page;
	    
	    // Kiểm tra searchName để xác định cần tìm kiếm hoặc lấy tất cả danh mục
	    if (!searchName.isEmpty()) {
	        // Thực hiện tìm kiếm theo tên danh mục
	        List<Categories> searchResult = categoriesService.searchCategoriesByName(searchName);
	        System.out.println(searchResult);
	        page = new PageImpl<>(searchResult);
	        System.out.println(page);
	    } else {
	        // Hiển thị tất cả danh mục nếu searchName trống
	        page = categoriesService.getAllCategoriesPage(pageNo);
	        return "redirect:/admin/categories";
	    }	    
	    model.addAttribute("listCategories", page.getContent());
	    model.addAttribute("currentPageCate", pageNo);
	    model.addAttribute("totalPageCate", page.getTotalPages());
	    return "page/categoriesAdmin";
	}
}
