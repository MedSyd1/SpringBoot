package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Acte;
import com.example.GestionCabinetDentaire.entities.Consultation;
import com.example.GestionCabinetDentaire.entities.DossierMedical;
import com.example.GestionCabinetDentaire.entities.InterventionMedecin;
import com.example.GestionCabinetDentaire.entities.Patient;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.enums.CategorieActe;
import com.example.GestionCabinetDentaire.enums.TypeConsultation;
import com.example.GestionCabinetDentaire.repos.ActeRepository;
import com.example.GestionCabinetDentaire.repos.ConsultationRepository;
import com.example.GestionCabinetDentaire.repos.DossierMedicalRepository;
import com.example.GestionCabinetDentaire.repos.InterventionMedecinRepository;
import com.example.GestionCabinetDentaire.repos.PatientRepository;
import com.example.GestionCabinetDentaire.repos.UtilisateurRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired 
    UtilisateurRepository uR;
    @Autowired
    PatientRepository pR;
    @Autowired 
    ConsultationRepository cR;
    @Autowired
    DossierMedicalRepository dR;
    @Autowired
    ActeRepository aR;
    @Autowired
    InterventionMedecinRepository iR;


    @Override
    public String displayConsultations(Utilisateur user, Long id, Model model) {
    
         Utilisateur oldUser = uR.findByEmail(user.getEmail());
        Patient patient = pR.findAll().stream().filter(p -> p.getId() == id).toList().get(0);
        
        
        List<Consultation> consultations = cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList();
        model.addAttribute("patient", patient);
        model.addAttribute("user", oldUser);
        model.addAttribute("consultations", consultations);

        return "consultation";


    }

    @Override
    public String addConsulatation(Utilisateur user, Long id, String acte, String pB, String date, String nbTeeths,
            String pp, Model model) {
       

                 Utilisateur oldUser = uR.findByEmail(user.getEmail());
        Patient patient     = pR.findById(id).orElse(null);
        DossierMedical  dossierMedical  = dR.findByMedecinTraitantId(oldUser.getId()).stream().filter(d -> d.getPatient().getId() == patient.getId()).toList().get(0);
        
        
        Consultation consultation = new Consultation();
        
        String[] parses = date.split("-");
        
        consultation.setDateConsultation(LocalDate.of(Integer.parseInt(parses[0]), Integer.parseInt(parses[1]), Integer.parseInt(parses[2]))
        );
        consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);
        consultation.setDossierMedical(dossierMedical);
        consultation.setInterventions(new ArrayList<>());
        consultation.setFactures(new ArrayList<>());
        consultation = cR.save(consultation);
        
        Acte acteEntity = aR.findByLibelle(acte);
        if (acteEntity == null){
            acteEntity = new Acte();
            acteEntity.setLibelle(acte);
            acteEntity.setPrixDeBase(Double.parseDouble(pB));
            acteEntity.setCategorieActe(CategorieActe.valueOf(acte));
            acteEntity = aR.save(acteEntity);
        }
        InterventionMedecin interventionMedecin = new InterventionMedecin();
        interventionMedecin.setNoteMedecin(acte);
        interventionMedecin.setPrixPatient(Double.parseDouble(pp));
        interventionMedecin.setDent(Long.parseLong(nbTeeths));
        interventionMedecin.setConsultation(consultation);
        interventionMedecin.setActe(acteEntity);
        interventionMedecin = iR.save(interventionMedecin);
        
        
        consultation.getInterventions().add(interventionMedecin);
        dossierMedical.getConsultations().add(consultation);
        dR.save(dossierMedical);
        model.addAttribute("user", uR.findByEmail(user.getEmail()));
        model.addAttribute("patient",patient);
        model.addAttribute("consultations", cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList());
        return "consultation";
    }

    @Override
    public String deleteConsultation(Utilisateur user, Long idConsultation, Long id, Model model) {
      
        List<InterventionMedecin> interventionMedecin = iR.findAll().stream().filter(i -> i.getConsultation().getIdConsultation() == idConsultation).toList();
        Patient patient = pR.findById(id).orElse(new Patient());
        iR.deleteAll(interventionMedecin);
        cR.deleteById(idConsultation);
        model.addAttribute("user", uR.findByEmail(user.getEmail()));
        model.addAttribute("patient",patient);
        model.addAttribute("consultations", cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList());
        return "consultation";

    }
    
    
}
