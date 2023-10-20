package com.fima.controller;

import com.fima.entity.Prices;
import com.fima.service.PricesServices;
import com.fima.service.ServicesService;
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
public class PricesAdminController {

    @Autowired
    public PricesServices pricesServices;

    @GetMapping("/prices/getAllPrices")
    public String pagePrices(Model model, @ModelAttribute("pricesEdit") Prices prices,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Prices> listPage = pricesServices.getAllPricesPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("list", pricesServices.getAllPrices());
        model.addAttribute("listPrices", listPage);
        return "page/pricesAdmin";
    }

    @GetMapping("/prices/edit/{id}")
    public String editPrices(Model model, @PathVariable("id") long id,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Prices> listPage = pricesServices.getAllPricesPage(pageNo);
        Optional<Prices> prices = pricesServices.getPricesById(id);
        model.addAttribute("pricesEdit", prices.orElse(new Prices()));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("list", pricesServices.getAllPrices());
        model.addAttribute("listPrices", listPage);
        return "page/pricesAdmin";
    }

    @RequestMapping("/prices/add")
    public String addPrices(@Valid @ModelAttribute("pricesEdit") Prices prices,
                            BindingResult bindingResult, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            Model model) {
        Page<Prices> listPage = pricesServices.getAllPricesPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listPrices", listPage);
            return "page/pricesAdmin";
        }
        prices.setDate_update(formatter.format(date));// Set date_update by date current
        pricesServices.addPrices(prices);
        return "redirect:/admin/prices/getAllPrices";
    }

    @RequestMapping("/prices/update")
    public String updatePrices(@Valid @ModelAttribute("pricesEdit") Prices prices,
                               BindingResult bindingResult, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               Model model) {
        Page<Prices> listPage = pricesServices.getAllPricesPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listPrices", listPage);
            return "page/pricesAdmin";
        }
        if ((prices.getDate_update() != null)) {
            prices.setDate_update(formatter.format(date));// Set date_update by date current
        }
        pricesServices.updatePrices(prices);
        return "redirect:/admin/prices/getAllPrices";
    }

    @RequestMapping("/prices/reset")
    public String deletePrices(Model model, @ModelAttribute("pricesEdit") Prices prices) {
        model.addAttribute("listPrices", pricesServices.getAllPrices());
        return "redirect:/admin/prices/getAllPrices";
    }

    @RequestMapping("/prices/delete/{id}")
    public String deletePrices(Model model, @PathVariable("id") long id) {
        pricesServices.deletePrices(id);
        List<Prices> listPrices = pricesServices.getAllPrices();
        model.addAttribute("listPrices", listPrices);
        return "redirect:/admin/prices/getAllPrices";
    }

    @PostMapping("/prices/search")
    public String searchPrices(Model model, @RequestParam("keyword") double searchName,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Prices> page;
        if (searchName > 0) {
            List<Prices> searchResult = pricesServices.findByPrices(searchName);
            page = new PageImpl<>(searchResult);
            model.addAttribute("listPrices", page.getContent());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
        } else {
            page = pricesServices.getAllPricesPage(pageNo);
        }
        model.addAttribute("listPrices", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        Prices pricesEdit = new Prices();
        model.addAttribute("pricesEdit", pricesEdit);
        return "page/pricesAdmin";
    }
}
