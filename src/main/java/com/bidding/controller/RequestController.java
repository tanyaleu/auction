package com.bidding.controller;

import com.bidding.model.Request;
import com.bidding.repository.RequestRepository;
import com.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/requestslist")
    public String getAllRequestsByUserId(Model model) {

        model.addAttribute("requests", requestRepository.findAll());
        return "requestslist";
    }


    @GetMapping("/request")
    public String requestForm(Model model) {
        model.addAttribute("newrequest", new Request());
        return "request";
    }


    @PostMapping("/request")
    public String requestSubmit(@ModelAttribute("newrequest") Request newrequest, HttpServletRequest httpRequest, Errors errors, Model model, Principal principal) {
        newrequest.setRequester(userRepository.findByLogin(principal.getName()));
        requestRepository.save(newrequest);
        model.addAttribute("request", newrequest);
        return "requestdata";
    }


}



