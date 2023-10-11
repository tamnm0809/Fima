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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	CategoriesService cateSer;

	String message;

	@GetMapping("/services/getAllServices")
	public String page(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
		System.out.println(servicesService.getAllServices().size());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listCate",cateSer.getAllCategories());
		model.addAttribute("listSer",servicesService.getAllServices());
		return "page/servicesAdmin";
	}

	@GetMapping("/services/edit")
	public String getServicesById(Model model, @RequestParam("id") long id,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
		Optional<Services> service = servicesService.getServicesById(id);
		model.addAttribute("listSer", servicesService.getAllServices());
		model.addAttribute("servicesEdit", service.get());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", listPage.getTotalPages());
		model.addAttribute("listSer", listPage);
		model.addAttribute("listCate",cateSer.getAllCategories());
		return "page/servicesAdmin";
	}

	@PostMapping("/services/add")
	public String addServices(Model model,@ModelAttribute Services ser,@RequestParam("categories") Long id) {
			Services serv=ser;
			serv.setId_categories(id);
			System.out.println(serv.getId_categories());
			servicesService.addServices(serv);
		return "redirect:/admin/services/getAllServices";
	}

	@PostMapping("/services/update")
	public String updateServices(Model model, @ModelAttribute Services ser,@RequestParam("categories") Long id) {
		Services serv=ser;
		serv.setId_categories(id);
		servicesService.updateServices(ser);
		model.addAttribute("listService", servicesService.getAllServices());
		return "redirect:/admin/services/getAllServices";
	}

	@RequestMapping("/services/reset")
	public String deleteServices(Model model, @ModelAttribute("servicesDetails") Services service) {
		service = null;
		return "redirect:/admin/services/getAllServices";
	}

	@GetMapping("/services/delete")
	public String deleteServices(Model model, @RequestParam("id") long id) {
		servicesService.deleteServices(id);
		return "redirect:/admin/services/getAllServices";
	}
	
}
