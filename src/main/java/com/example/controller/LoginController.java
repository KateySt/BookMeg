package com.example.controller;

import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    private Boolean isPhone = false;
    private Boolean isEmail = false;
    private final String rexPhone="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
    private final String rexEmail="\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

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
        isPhone = Pattern.matches(rexPhone, userPhone);
        isEmail = Pattern.matches(rexEmail, userEmail);
        if (isPhone == false | isEmail == false) {
            return "createAccount";
        }
        User user = new User(true, nameUser, userPhone, userEmail, userPassword);
        user.addRole(userRepository.findOne(2).getRoles().get(0));
        userRepository.save(user);
        return "index";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        return "index";
    }

}