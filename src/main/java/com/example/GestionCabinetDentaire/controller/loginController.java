package com.example.GestionCabinetDentaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.GestionCabinetDentaire.service.servicesDeclaration.LoginService;


@Controller
@SessionAttributes("user")
public class loginController {

    @Autowired
    LoginService ls;

    @GetMapping("/")
    public String login(){
        return ls.redirectToLogin();
    }

    @PostMapping("/login")
    public String register(@RequestParam String email , @RequestParam String password,Model model)
    {
       return ls.register(email, password, model);
    }
    

    




}
