package com.example.GestionCabinetDentaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.GestionCabinetDentaire.entities.Facture;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.repos.FactureRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.BillService;


@Controller
@SessionAttributes("user")
public class BillController {
    
    @Autowired
    BillService billService;
    @Autowired
    FactureRepository fR;   

    @PostMapping("/addFacture/{id}")
            public String addFacture(@ModelAttribute("user") Utilisateur user,Model model,@PathVariable("id") Long id,
            @RequestParam("idc") String idConsultation,
            @RequestParam("date") String date,
            @RequestParam("etat") String etat,
            @RequestParam("reste") String reste,
            @RequestParam("montant") String montant,
            @RequestParam("totale") String totale
            
            ){
                return billService.addBill(user, model, id, idConsultation, date, etat, reste, montant, totale) ;
            }
            
            
            @GetMapping("/deleteFacture/{idf}/{id}")
            public String deleteFacture(@ModelAttribute("user") Utilisateur user,@PathVariable("id") Long id,@PathVariable("idf") Long idBill,Model model){
                return billService.deleteBill(user, id, idBill, model);
            }
            
            @GetMapping("/caisse")
            public String caisse(@ModelAttribute("user") Utilisateur user,Model model
            ){
              
                List<Facture> factures = fR.findAll().stream().filter(f -> f.getConsultation().getDossierMedical().getMedecinTraitant().getId() == user.getId()).toList();
                model.addAttribute("user", user);
                model.addAttribute("factures", factures);
                return "bill";
            }
}
