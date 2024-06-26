package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findUtilisateurByNom(String nom);
    Utilisateur findByEmail(String email);
}