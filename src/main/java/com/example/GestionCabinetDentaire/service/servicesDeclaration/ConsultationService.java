package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Utilisateur;

public interface ConsultationService {

    String displayConsultations(Utilisateur user,Long id,Model model);
    String addConsulatation(Utilisateur user,Long id, String acte,String pB , String date,String nbTeeths , String pp,Model model);
    String deleteConsultation(Utilisateur user,Long idConsultation,Long id,Model model);
}
