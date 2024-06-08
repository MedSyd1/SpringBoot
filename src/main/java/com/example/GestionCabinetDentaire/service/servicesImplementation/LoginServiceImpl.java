package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Patient;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.repos.PatientRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired 
    UtilisateurServiceImp uS;
    @Autowired
    PatientRepository pR;

    @Override
    public String redirectToLogin() {
        return "login";
    }

    @Override
    public String register(String email, String password, Model model) {
     
        Utilisateur utilisateur = uS.checkUtilisateur(email, password);
        model.addAttribute("user", utilisateur);
        if (utilisateur == null)
            return "login";

        List<Patient> patients = pR.findAll().stream().filter(p -> p.getDossierMedical().getMedecinTraitant().getId() == utilisateur.getId()).toList();

        model.addAttribute("patients", patients);

        List<Patient> todayPatients = patients.stream().filter(p -> p.getDossierMedical().getConsultations().size() != 0).filter(p -> p.getDossierMedical().getConsultations().get(0).getDateConsultation().equals(LocalDate.now())).toList();
        model.addAttribute("todayPatients", todayPatients);
        return "home";

    } 
    
}
