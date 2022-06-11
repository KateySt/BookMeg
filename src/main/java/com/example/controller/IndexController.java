package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String getHomePage( Model model) {
        //model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/")
    public String getStartPage( Model model) {
        return "index";
    }
}
