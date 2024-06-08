package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Consultation;
import com.example.GestionCabinetDentaire.entities.Facture;
import com.example.GestionCabinetDentaire.entities.Patient;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.repos.ConsultationRepository;
import com.example.GestionCabinetDentaire.repos.FactureRepository;
import com.example.GestionCabinetDentaire.repos.PatientRepository;
import com.example.GestionCabinetDentaire.repos.UtilisateurRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.FinancialSituationService;


@Service
public class FinancialSituationServiceImpl implements FinancialSituationService {

    @Autowired
    UtilisateurRepository uR;
    @Autowired
    PatientRepository pR;
    @Autowired 
    ConsultationRepository cR;
    @Autowired
    FactureRepository fR;  

    @Override
    public String situation(Utilisateur user, Long id, Model model) {
        
                Utilisateur oldUser = uR.findByEmail(user.getEmail());
                Patient patient = pR.findAll().stream().filter(p -> p.getId() == id).toList().get(0);
                List<Consultation> consultations = cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList();
                ArrayList<Long> ids = new ArrayList<>();
                for (Consultation c  : consultations)
                ids.add(c.getIdConsultation());
                List<Facture> factures = fR.findAll();
                if (factures.size() != 0)
                factures = factures.stream().filter(f -> ids.contains(f.getConsultation().getIdConsultation())).toList();
                model.addAttribute("factures", factures);
                model.addAttribute("user", oldUser);
                model.addAttribute("patient", patient);
                return "financial";
    }
    
}
