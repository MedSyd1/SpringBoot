package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.time.LocalDate;
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
import com.example.GestionCabinetDentaire.service.servicesDeclaration.BillService;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.PatientService;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    UtilisateurRepository uR;

    @Autowired
    PatientRepository pR;

    @Autowired
    PatientService pS;

    @Autowired 
    ConsultationRepository cR;

    @Autowired
    FactureRepository fR;  


    @Override
    public String addBill(Utilisateur user, Model model, Long id, String idConsultation, String date, String etat,
            String reste, String montant, String totale) {


                Patient patient = pR.findById(id).orElse(null);
                
                Consultation consultation =  cR.findById(Long.parseLong(idConsultation)).orElse(null);
                
                Facture facture = new Facture();
                facture.setConsultation(consultation);
                
                String[] parses = date.split("-");
                facture.setDateFacturation(LocalDate.of(Integer.parseInt(parses[0]), Integer.parseInt(parses[1]), Integer.parseInt(parses[2]))
                );
                
                facture.setEtat(etat);
                facture.setMontantRestant(Double.parseDouble(reste));
                facture.setMontantPaye(Double.parseDouble(montant));
                facture.setMontantTotal(Double.parseDouble(totale));
                
                fR.save(facture);
                
                
                List<Consultation> consultations = cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList();
                
                ArrayList<Long> ids = new ArrayList<>();
                for (Consultation c  : consultations)
                ids.add(c.getIdConsultation());
                
                
                List<Facture> factures = fR.findAll();
                if (factures.size() != 0)
                factures = factures.stream().filter(f -> ids.contains(f.getConsultation().getIdConsultation())).toList();
                
                model.addAttribute("user", uR.findByEmail(user.getEmail()));
                model.addAttribute("patient", patient);
                model.addAttribute("factures", factures);
                return "financial";

    }


    @Override
    public String deleteBill(Utilisateur user, Long id, Long idBill, Model model) {

        Patient patient = pR.findById(id).orElse(null);
        fR.deleteById(idBill);
        
        
        List<Consultation> consultations = cR.findAll().stream().filter(c -> c.getDossierMedical().getPatient().getId() == patient.getId()).toList();
        
        ArrayList<Long> ids = new ArrayList<>();
        for (Consultation c  : consultations)
        ids.add(c.getIdConsultation());
        
        
        List<Facture> factures = fR.findAll();
        if (factures.size() != 0)
        factures = factures.stream().filter(f -> ids.contains(f.getConsultation().getIdConsultation())).toList();
        
        model.addAttribute("user", uR.findByEmail(user.getEmail()));
        model.addAttribute("patient", patient);
        model.addAttribute("factures", factures);        
        return "financial";

    }
    
}
