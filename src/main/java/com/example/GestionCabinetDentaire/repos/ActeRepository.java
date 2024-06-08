package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Acte;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActeRepository extends JpaRepository<Acte,Long> {
    Acte findByLibelle(String libelle);
}
