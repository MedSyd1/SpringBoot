package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import java.util.List;

import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Patient;
import com.example.GestionCabinetDentaire.entities.Utilisateur;



public interface PatientService {

    List<Patient> getAllPatient();
    List<Patient> getDentistPatients(Long dentistId);
    List<Patient> getFilteredTodayPatients(Long dentistId);
    Patient   addPatient(Utilisateur user,String nom, String prenom, String dateNaissance,
    String sexe,
    String mutuelle,
    String groupeSanguin,
    String profession
    );
    
    String displayPatients(Utilisateur user,Model model);
    String addPatient(Utilisateur user,Model model);
    String form(Utilisateur user,String fname,String lname , 
    String date,String sexe,String mutuelle,String typeBlood, String profession
    ,Model model);
    String deletePatient(Utilisateur user,Long id,Model model);
}
