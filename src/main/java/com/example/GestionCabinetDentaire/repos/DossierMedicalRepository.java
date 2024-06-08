package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.DossierMedical;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,String> {
    List<DossierMedical> findByMedecinTraitantId(Long id);
}
