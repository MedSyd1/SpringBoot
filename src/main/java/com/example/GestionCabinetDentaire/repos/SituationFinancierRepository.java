package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationFinancierRepository extends JpaRepository<SituationFinanciere,Long> {
}
