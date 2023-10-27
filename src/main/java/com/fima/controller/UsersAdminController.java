package com.fima.controller;

import com.fima.entity.Users;
import com.fima.service.UserService;
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
public class UsersAdminController {

    @Autowired
    private UserService userServices;

    @GetMapping("/users/getAllUsers")
    public String pageUsers(Model model, @ModelAttribute("usersEdit") Users users,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Users> listPage = userServices.getAllUsersPage(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listUsers", listPage);
        return "page/usersAdmin";
    }

    @GetMapping("/users/edit/{username}")
    public String editUsers(Model model, @PathVariable("username") String username, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Users> listPage = userServices.getAllUsersPage(pageNo);
        Optional<Users> users = userServices.getUsersById(username);
        model.addAttribute("usersEdit", users.orElse(new Users()));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", pageNo = 1);
        model.addAttribute("totalPage", listPage.getTotalPages());
        model.addAttribute("listUsers", listPage);
        return "page/usersAdmin";
    }

    @RequestMapping("/users/update")
    public String updateCategories(@Valid @ModelAttribute("usersEdit") Users users, BindingResult bindingResult,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Page<Users> listPage = userServices.getAllUsersPage(pageNo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");// Format date
        Date date = new Date(); // Get date current
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("firstPage", pageNo = 1);
            model.addAttribute("totalPage", listPage.getTotalPages());
            model.addAttribute("listUsers", listPage);
            return "page/usersAdmin";
        }
        if ((users.getDate_start_cre() != null)) {
            users.setDate_start_cre(formatter.format(date));// Set date_update by date current
        }
        users.setAvatar("");
        userServices.updateUsers(users);
        return "redirect:/admin/users/getAllUsers";
    }

    @RequestMapping(value = "/users/reset", method = {RequestMethod.GET, RequestMethod.POST})
    public String resetForm(Model model, @ModelAttribute("usersEdit") Users users) {
        return "redirect:/admin/users/getAllUsers";
    }

    @RequestMapping(value = "/users/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUsers(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Users> page;
        if (keyword != null && !keyword.isEmpty()) {
            page = userServices.findByNameLike(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } else {
            page = userServices.getAllUsersPage(pageNo);
        }
        model.addAttribute("listUsers", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("usersEdit", new Users());
        return "page/usersAdmin";
    }
}
