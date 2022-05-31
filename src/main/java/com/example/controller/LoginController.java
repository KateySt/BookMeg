package com.example.controller;

import com.example.db.entity.Role;
import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

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

   /* @PostMapping("/login")
    public String postLogin(User user, Map<String, Object> model) {
        ArrayList<User> userRepositoryOne = new ArrayList<>(userRepository.findByName(user.getNameUser()));
        if (userRepositoryOne == null) {
            return "createAccount";
        }
        user.setActive(true);
        user.setRole(userRepository.findOne(2).getRole());
        userRepository.save(user);
        return "index";
    }


    */
}
