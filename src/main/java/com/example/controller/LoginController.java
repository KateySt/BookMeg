package com.example.controller;

import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLoginAccount(Model model) {
        return "login";
    }

    @GetMapping("/login/creatAccount")
    public String getLoginCreateAccount(Model model) {
        return "createAccount";
    }

    @PostMapping("/login/creatAccount")
    public String postCreateLogin(@RequestParam String nameUser, @RequestParam String userPhone, @RequestParam String userEmail, @RequestParam String userPassword, Model model) {
        User user = new User(true, nameUser, userPhone, userEmail, userPassword);
        user.addRole(userRepository.findOne(2).getRoles().get(0));
        userRepository.save(user);
        return "index";
    }

    @PostMapping("/login")
    public String postLogin(User user, Model model) {
        return "index";
    }

}