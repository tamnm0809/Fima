package com.fima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class indexAdminController {

    @GetMapping("/")
    public String index() {
        return "page/indexAdmin";
    }
}
