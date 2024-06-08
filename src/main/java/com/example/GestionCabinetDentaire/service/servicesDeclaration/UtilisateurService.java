package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import com.example.GestionCabinetDentaire.entities.Utilisateur;

public interface UtilisateurService {

    Utilisateur checkUtilisateur(String email,String passowrd);
    
}
