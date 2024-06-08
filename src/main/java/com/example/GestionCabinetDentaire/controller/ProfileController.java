package com.example.GestionCabinetDentaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.repos.UtilisateurRepository;

import jakarta.transaction.Transactional;



@Controller
@SessionAttributes("user")
public class ProfileController {
    
    @Autowired
    UtilisateurRepository utilisateurRepository;

     @GetMapping("/profile")
    public String profile(@ModelAttribute("user") Utilisateur user,Model model){        
        model.addAttribute("user", utilisateurRepository.findByEmail(user.getEmail()));
        return "profile";
    }

    @GetMapping("/modiferProfile")
    public String modifierProfile(@ModelAttribute("user") Utilisateur user,Model model){
        model.addAttribute("user", user);
        return "modifyProfile";
    }

    @PostMapping("/enregisterProfile")
    @Transactional
    public String redirectToProfile(@ModelAttribute("user") Utilisateur user,Model model){
        utilisateurRepository.save(user);
        model.addAttribute("user", user);
        return "profile";
    }

}
