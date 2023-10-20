package com.fima.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fima.entity.Prices;
import com.fima.service.PricesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ServicesAdminController {

    @Autowired
    public ServicesService servicesService;

    @GetMapping("/")
    public String index() {
        return "page/indexAdmin";
    }

    @GetMapping("/services/getAllServices")
    public String pageServices(Model model, @ModelAttribute("servicesEdit") Services services,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
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
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listService", listPage);
        return "page/servicesAdmin";
    }

    @RequestMapping("/services/add")
    public String addServices(@Valid @ModelAttribute("servicesEdit") Services service, BindingResult bindingResult,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listService", listPage);
            return "page/servicesAdmin";
        }
        service.setDate_update(formatter.format(date));// Set date_update by date current
        servicesService.addServices(service);
        return "redirect:/admin/services/getAllServices";
    }

    @RequestMapping("/services/update")
    public String updateCategories(@Valid @ModelAttribute("servicesEdit") Services service, BindingResult bindingResult,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listService", listPage);
            return "page/servicesAdmin";
        }
        if ((service.getDate_update() != null)) {
            service.setDate_update(formatter.format(date));// Set date_update by date current
        }
        servicesService.updateServices(service);
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

    @PostMapping("/services/search")
    public String searchServices(Model model, @RequestParam("keyword") String keyword,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Services> page;
        if (!keyword.isEmpty()) {
            List<Services> searchResult = servicesService.findByNameLike(keyword);
            page = new PageImpl(searchResult);
            System.out.println(page);
            model.addAttribute("listService", page.getContent());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
        } else {
            page = servicesService.getAllServicesPage(pageNo);
        }
        model.addAttribute("listService", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        Services servicesEdit = new Services();
        model.addAttribute("servicesEdit", servicesEdit);
        return "page/servicesAdmin";
    }
}