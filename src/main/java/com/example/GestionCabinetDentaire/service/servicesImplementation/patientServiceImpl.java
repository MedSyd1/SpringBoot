package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Consultation;
import com.example.GestionCabinetDentaire.entities.Dentiste;
import com.example.GestionCabinetDentaire.entities.DossierMedical;
import com.example.GestionCabinetDentaire.entities.Facture;
import com.example.GestionCabinetDentaire.entities.InterventionMedecin;
import com.example.GestionCabinetDentaire.entities.Patient;
import com.example.GestionCabinetDentaire.entities.Personne;
import com.example.GestionCabinetDentaire.entities.SituationFinanciere;
import com.example.GestionCabinetDentaire.entities.Utilisateur;
import com.example.GestionCabinetDentaire.enums.GroupeSanguin;
import com.example.GestionCabinetDentaire.enums.TypeConsultation;
import com.example.GestionCabinetDentaire.repos.ConsultationRepository;
import com.example.GestionCabinetDentaire.repos.DossierMedicalRepository;
import com.example.GestionCabinetDentaire.repos.FactureRepository;
import com.example.GestionCabinetDentaire.repos.InterventionMedecinRepository;
import com.example.GestionCabinetDentaire.repos.PatientRepository;
import com.example.GestionCabinetDentaire.repos.PersonneRepository;
import com.example.GestionCabinetDentaire.repos.SituationFinancierRepository;
import com.example.GestionCabinetDentaire.repos.UtilisateurRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.PatientService;

@Service
public class patientServiceImpl implements PatientService {


    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SituationFinancierRepository situationFinancierRepository;
    @Autowired 
    DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired 
    UtilisateurRepository uR;
    @Autowired
    PersonneRepository pR;

    @Autowired
    FactureRepository fR;

    @Autowired
    InterventionMedecinRepository iR;

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getDentistPatients(Long dentistId) {
        return getAllPatient().stream().filter(p -> p.getDossierMedical().getMedecinTraitant().getId() == dentistId).toList();
    }

    @Override
    public List<Patient> getFilteredTodayPatients(Long dentistId) {
        return getDentistPatients(dentistId).stream().filter(p -> p.getDossierMedical().getConsultations().get(0).getDateConsultation().equals(LocalDate.now())).toList();
    }

    @Override
    public Patient addPatient(Utilisateur user, String nom, String prenom, String dateNaissance, String sexe,
            String mutuelle, String groupeSanguin, String profession) {
            SituationFinanciere situationFinanciere = new SituationFinanciere();   
            situationFinanciere.setDateCreation(LocalDate.now()); 
            situationFinanciere =  situationFinancierRepository.save(situationFinanciere);

            DossierMedical dossierMedical = new DossierMedical();
            dossierMedical.setNumeroDossier(UUID.randomUUID().toString());
            dossierMedical.setDateCreation(LocalDate.now());
            dossierMedical.setMedecinTraitant((Dentiste) user);
            dossierMedical.setSituationFinanciere(situationFinanciere);
            dossierMedical = dossierMedicalRepository.save(dossierMedical);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(LocalDate.now());    
            consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);    
            consultation.setDossierMedical(dossierMedical);
            consultationRepository.save(consultation);

            Patient patient = new Patient();
            patient.setDossierMedical(dossierMedical);
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setSexe(sexe);
            String[] parses = dateNaissance.split("-");
            patient.setDateNaissance( LocalDate.of(Integer.parseInt(parses[0]), Integer.parseInt(parses[1]), Integer.parseInt(parses[2])));
            patient.setGroupeSanguin(GroupeSanguin.valueOf(groupeSanguin));
            patient.setProfession(profession);    

            return patientRepository.save(patient);

    }

    @Override
    public String displayPatients(Utilisateur user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("patients", getDentistPatients(user.getId()));
        return "patients";
    }

    @Override
    public String addPatient(Utilisateur user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("patients",   model.addAttribute("patients", getDentistPatients(user.getId())));
        return "addPatient";

    }

    @Override
    public String form(Utilisateur user, String fname, String lname, String date,String sexe, String mutuelle, String typeBlood,
            String profession, Model model) {
                addPatient(user, fname, lname, date, sexe, mutuelle, typeBlood, profession);
                model.addAttribute("patients", getDentistPatients(user.getId()));
                model.addAttribute("user", user);
                return "patients";

    }

    @Override
    public String deletePatient(Utilisateur user, Long id, Model model) {


        Utilisateur oldUser = uR.findByEmail(user.getEmail());

        
        Patient patient = getDentistPatients(oldUser.getId()).stream().filter(p -> p.getId() == id).toList().get(0);
        
        Personne personne = pR.findAll().stream().filter(p -> p.getId() == patient.getId()).toList().get(0);
        
        DossierMedical dossierMedical = dossierMedicalRepository.findAll().stream().filter(d -> 
        d.getNumeroDossier()
        .equals(patient.getDossierMedical()
        .getNumeroDossier())).toList().get(0);
        
        List<Consultation> consultations = consultationRepository.findAll().stream().filter(c -> c.getDossierMedical().getNumeroDossier().equals(dossierMedical.getNumeroDossier())).toList();

        ArrayList<Long> ids = new ArrayList<>();
        
        for (Consultation c  : consultations)
            ids.add(c.getIdConsultation());


        SituationFinanciere situationFinanciere = situationFinancierRepository.findAll().stream().filter(
            s -> s.getIdSituationFinanciere() == dossierMedical.getSituationFinanciere().getIdSituationFinanciere()
            ).toList().get(0);

        List<Facture> factures = fR.findAll().stream().filter(f -> f.getConsultation().getDossierMedical().getPatient().getId() == patient.getId()).toList();

            List<InterventionMedecin> interventionMedecins = iR.findAll().stream().filter(i -> ids.contains(i.getConsultation().getIdConsultation())).toList();

            iR.deleteAll();
            fR.deleteAll(factures);
            consultationRepository.deleteAll(consultations);
            dossierMedicalRepository.delete(dossierMedical);
            situationFinancierRepository.delete(situationFinanciere);
            patientRepository.delete(patient);
            pR.delete(personne);
            model.addAttribute("user", oldUser);
            model.addAttribute("patients", getDentistPatients(oldUser.getId()));
            return "patients";  

    }
    
 


    
}
