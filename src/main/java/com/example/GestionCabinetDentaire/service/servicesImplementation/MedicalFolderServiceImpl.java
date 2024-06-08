package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.DossierMedical;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.repos.DossierMedicalRepository;
import com.example.GestionCabinetDentaire.repos.UtilisateurRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.MedicalFolderService;

@Service
public class MedicalFolderServiceImpl  implements MedicalFolderService{


     @Autowired
    UtilisateurRepository uR;

    @Autowired 
    DossierMedicalRepository dR;

    @Override
    public String medicalfolder(Utilisateur user, Long id, Model model) {
             
        Utilisateur oldUser = uR.findByEmail(user.getEmail());
        model.addAttribute("user", oldUser);
        model.addAttribute("id", id);
        List<DossierMedical> listDossierMedical = dR.findByMedecinTraitantId(oldUser.getId());
        DossierMedical dossierMedical2 = listDossierMedical.stream().filter(d -> d.getPatient().getId() == id).toList().get(0);
        model.addAttribute("dossier", dossierMedical2);
        
        return "medicalFolder";

    }
    
}
