package com.fima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fima.entity.Services;
import com.fima.service.ServicesService;

@RestController
@RequestMapping("/admin/services")
public class ServicesAdminController {

	@Autowired
	ServicesService servicesService;

	@GetMapping("/getAllServices")
	public String getAllServices(Model model) {
		List<Services> service = servicesService.getAllServices();
		model.addAttribute("services", service);
		return "";
	}
	//

	@GetMapping("/getServicesById")
	public Services getServicesById(@RequestParam("id") long id) {
		return servicesService.getServicesById(id);
	}

	@PostMapping("/add")
	public Services addServices(@RequestBody Services servicesDetail) {
		return servicesService.addServices(servicesDetail);
	}

	@PutMapping("/update")
	public Services updateServices(@RequestParam("id") long id, @RequestBody Services servicesDetail) {
		return servicesService.updateServices(id, servicesDetail);
	}

//
	@RequestMapping("/delete/{id}")
	public String deleteServices(@PathVariable("id") long id) {
		servicesService.deleteServices(id);
		return "";
	}
}
