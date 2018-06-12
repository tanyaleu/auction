package com.bidding.controller;


import com.bidding.model.User;
import com.bidding.repository.UserRepository;
import com.bidding.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/user/registration")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "userenter";
    }

    @PostMapping("/user/registration")
    public String userSubmit(@ModelAttribute("user") User user, HttpServletRequest httpRequest, Errors errors, Model model) {

        userDetailsService.saveUser(user, "ROLE_USER");
        model.addAttribute("user", user);
        return "userdata";
    }


    @GetMapping("/userslist")
    public String getAllusers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userslist";
    }


}
