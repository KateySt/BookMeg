package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {
    @GetMapping("/author")
    public String getAuthorPage( Model model) {
        return "author";
    }
}
