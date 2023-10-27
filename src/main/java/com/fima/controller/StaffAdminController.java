package com.fima.controller;

import com.fima.entity.Services;
import com.fima.entity.Staff;
import com.fima.service.ServicesService;
import com.fima.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class StaffAdminController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private ServicesService servicesService;

    private Services services;

    @GetMapping("/staff/getAllStaff")
    public String pageUsers(Model model, @ModelAttribute("staffEdit") Staff staff,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Staff> listPage = staffService.getAllStaffPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listStaff", listPage);
        model.addAttribute("listServices", servicesService.getAllServices());
        return "page/staffAdmin";
    }

    @GetMapping("/staff/edit/{username}")
    public String editUsers(Model model, @ModelAttribute Staff staffModel, @PathVariable("username") String username, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Staff> listPage = staffService.getAllStaffPage(pageNo);
        Optional<Staff> staff = staffService.getStaffById(username);
        model.addAttribute("staffEdit", staff.orElse(new Staff()));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listStaff", listPage);
        model.addAttribute("listServices", servicesService.getAllServices());
        return "page/staffAdmin";
    }

    @RequestMapping("/staff/update")
    public String updateCategories(@Valid @ModelAttribute("staffEdit") Staff staff, BindingResult bindingResult,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Staff> listPage = staffService.getAllStaffPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listUsers", listPage);
            return "page/staffAdmin";
        }
        if ((staff.getDate_start_cre() != null)) {
            staff.setDate_start_cre(formatter.format(date));// Set date_update by date current
        }
        staff.setAvatar("");
        staff.setImage_card("");
        staffService.updateStaff(staff);
        return "redirect:/admin/staff/getAllStaff";
    }

    @RequestMapping(value = "/staff/reset", method = {RequestMethod.GET, RequestMethod.POST})
    public String resetForm(Model model, @ModelAttribute("staffEdit") Staff staff) {

        return "redirect:/admin/staff/getAllStaff";
    }

    @RequestMapping(value = "/staff/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchStaff(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Staff> page;
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("keyword", keyword);
            page = staffService.findByNameLike(keyword, pageNo);
        } else {
            page = staffService.getAllStaffPage(pageNo);
        }
        model.addAttribute("listStaff", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("staffEdit", new Staff());
        return "page/staffAdmin";
    }
}
