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
import com.example.GestionCabinetDentaire.service.servicesDeclaration.PatientService;

@Controller
@SessionAttributes("user")
public class PatientController {


    @Autowired
    PatientService ps;

    @GetMapping("/patients")
    public String listPatients(@ModelAttribute("user") Utilisateur user,Model model){
        model.addAttribute("user", user);
        model.addAttribute("patients", ps.getDentistPatients(user.getId()));
        return "patients";
    }
    
    @GetMapping("/ajouterPatient")
    public String ajouterPatient(@ModelAttribute("user") Utilisateur user,Model model){
        return ps.addPatient(user, model);
    }

    @PostMapping("/patients")
    public String patientForm(@ModelAttribute("user") Utilisateur user,  @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("dateNaissance") String dateNaissance,
            @RequestParam("sexe") String sexe,
            @RequestParam("mutuelle") String mutuelle,
            @RequestParam("groupeSanguin") String groupeSanguin,
            @RequestParam("profession") String profession,
            Model model){
           return ps.form(user, nom, prenom, dateNaissance, sexe, mutuelle, groupeSanguin, profession, model);
    }
    

     @GetMapping("/deletePatient/{id}")
    public String deletePatient(@ModelAttribute("user") Utilisateur user,  @PathVariable("id") Long id , Model model){
        
            return ps.deletePatient(user, id, model );    
        }

    
}
