package com.fima.controller;

import com.fima.entity.Services;
import com.fima.service.CategoriesService;
import com.fima.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ServicesAdminController {

    @Autowired
    public ServicesService servicesService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/services/getAllServices")
    public String pageServices(Model model, @ModelAttribute("servicesEdit") Services services,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listService", listPage);
        model.addAttribute("listCategory", categoriesService.getAllCategory());
        return "page/servicesAdmin";
    }

    @GetMapping("/services/edit/{id}")
    public String getServicesById(Model model, @PathVariable("id") long id,
                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        Optional<Services> service = servicesService.getServicesById(id);
        model.addAttribute("servicesEdit", service.orElse(new Services()));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listService", listPage);
        model.addAttribute("listCategory", categoriesService.getAllCategory());
        return "page/servicesAdmin";
    }

    @RequestMapping("/services/add")
    public String addServices(@Valid @ModelAttribute("servicesEdit") Services service, BindingResult bindingResult,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Services> listPage = servicesService.getAllServicesPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        if ((bindingResult.hasErrors())
                || (servicesService.findByName(service.getName()) != null)) {
            String message = "Bạn chưa điền đầy đủ thông tin hoặc trùng lặp tên trên biểu mẫu!";
            model.addAttribute("message", message);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listService", listPage);
            return "page/servicesAdmin";
        }
        service.setImage("");
        service.setDate_update(formatter.format(date));
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
        service.setImage("");
        servicesService.updateServices(service);
        return "redirect:/admin/services/getAllServices";
    }

    @RequestMapping("/services/reset")
    public String deleteServices(Model model, @ModelAttribute("servicesDetails") Services service) {
        service.setImage("");
        return "redirect:/admin/services/getAllServices";
    }

    @GetMapping("/services/delete/{id}")
    public String deleteServices(Model model, @PathVariable("id") long id) {
        servicesService.deleteServices(id);
        List<Services> listServices = servicesService.getAllServices();
        model.addAttribute("listService", listServices);
        return "redirect:/admin/services/getAllServices";
    }

    @RequestMapping(value = "/services/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchServices(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Services> page;
        if (keyword != null && !keyword.isEmpty()) {
            page = servicesService.findByNameLike(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } else {
            page = servicesService.getAllServicesPage(pageNo);
        }
        model.addAttribute("listService", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("servicesEdit", new Services());
        return "page/servicesAdmin";
    }
}