package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Utilisateur;

public interface FinancialSituationService {
    
    String situation(Utilisateur user,Long id,Model model);
    
}
