package com.example.controller;

import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getLoginAccount(Model model) {
        return "login";
    }

    @GetMapping("/login/createAccount")
    public String getLoginCreateAccount(Model model) {
        return "createAccount";
    }

    @PostMapping("/login/createAccount")
    public String postCreateLogin(@RequestParam String nameUser, @RequestParam String userPhone, @RequestParam String userEmail, @RequestParam String userPassword, Model model) {
        User user = new User(nameUser, userPhone, userEmail, userPassword);
        userRepository.save(user);
        return "index";
    }

    @PostMapping("/login")
    public String postLogin(User user,Model model) {
        return "index";
    }

}
