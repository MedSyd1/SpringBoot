package com.example.GestionCabinetDentaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.service.servicesImplementation.FinancialSituationServiceImpl;

@Controller
@SessionAttributes("user")
public class FinancialSituationController {
            @Autowired
            FinancialSituationServiceImpl fS;
            @GetMapping("/situationFinanciere/{id}")
            public String Situation(@ModelAttribute("user") Utilisateur user,@PathVariable("id") Long id,Model model){
                return fS.situation(user, id, model);   
            }
            
}
