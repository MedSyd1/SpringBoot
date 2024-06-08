package com.example.GestionCabinetDentaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.ConsultationService;


@Controller
@SessionAttributes("user")
public class ConsultationController {
    
    @Autowired
    ConsultationService cS;

     @GetMapping("/consultation/{id}")
    public String consultation(@ModelAttribute("user")Utilisateur user ,@PathVariable("id") Long id ,Model model){
        return cS.displayConsultations(user, id, model);
    }
    
    @PostMapping("/addConsultation/{id}")
    public String addConsultation(@ModelAttribute("user") Utilisateur user,@PathVariable("id") Long id
    ,@RequestParam("acte") String acte
    ,@RequestParam("prixBase") String prixBase
    ,@RequestParam("date") String date
    ,@RequestParam("nbDents") String nbDents
    ,@RequestParam("prixPatient") String prixPatient
    ,Model model
    ){
        return cS.addConsulatation(user, id, acte, prixBase, date, nbDents, prixPatient, model);
    }
    
    @GetMapping("/deleteConsultation/{idc}/{id}")
    public String supprimerConsultation(
        @ModelAttribute("user") Utilisateur user,
        @PathVariable("idc") Long idc,
        @PathVariable("id") Long id,
        Model model
        ){
            return cS.deleteConsultation(user, idc, id, model);
        }
        

}
