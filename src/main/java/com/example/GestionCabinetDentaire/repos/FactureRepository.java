package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
