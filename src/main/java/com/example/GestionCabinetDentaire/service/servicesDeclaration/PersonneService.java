package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import com.example.GestionCabinetDentaire.entities.Personne;


public interface PersonneService {

    Personne findPersonneByEmail(String email);
    void deletePersonneById(Long id);
    Personne findPersonneById(Long id);

    
}
