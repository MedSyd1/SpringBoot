package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Utilisateur;

public interface MedicalFolderService {

    
String medicalfolder(Utilisateur user,Long id,Model model);


    
}
