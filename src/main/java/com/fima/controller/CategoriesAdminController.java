package com.fima.controller;

import com.fima.entity.Categories;
import com.fima.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class CategoriesAdminController {

    @Autowired
    public CategoriesService categoriesService;

    @GetMapping("/categories/getAllCategories")
    public String pageCategory(Model model, @ModelAttribute("categoriesEdit") Categories categories,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listCategory", listPage);
        return "page/categoryAdmin";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") long id,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
        Optional<Categories> category = categoriesService.getCategoryById(id);
        model.addAttribute("categoriesEdit", category.orElse(new Categories()));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listCategory", listPage);
        return "page/categoryAdmin";
    }

    @RequestMapping("/categories/add")
    public String addCategory(@Valid @ModelAttribute("categoriesEdit") Categories categories,
                              BindingResult bindingResult, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              Model model) {
        Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listCategory", listPage);
            return "page/categoryAdmin";
        }
        categories.setDate_update(formatter.format(date));// Set date_update by date current
        categoriesService.addCategory(categories);
        return "redirect:/admin/categories/getAllCategories";
    }

    @RequestMapping("/categories/update")
    public String updateCategories(@Valid @ModelAttribute("categoriesEdit") Categories categories,
                                   BindingResult bindingResult, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   Model model) {
        Page<Categories> listPage = categoriesService.getAllCategoryPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listCategory", listPage);
            return "page/CategoryAdmin";
        }
        if ((categories.getDate_update() != null)) {
            categories.setDate_update(formatter.format(date));// Set date_update by date current
        }
        categoriesService.updateCategory(categories);
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

    @PostMapping("/categories/search")
    public String searchCategories(Model model, @RequestParam("keyword") String searchName,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Categories> page;
        if (!searchName.isEmpty()) {
            List<Categories> searchResult = categoriesService.searchCategoryByName(searchName);
            page = new PageImpl<>(searchResult);
            model.addAttribute("listCategory", page.getContent());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
        } else {
            page = categoriesService.getAllCategoryPage(pageNo);
        }
        model.addAttribute("listCategory", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        Categories categoriesEdit = new Categories();
        model.addAttribute("categoriesEdit", categoriesEdit);
        return "page/categoryAdmin";
    }


}
