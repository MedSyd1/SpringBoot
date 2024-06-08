package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
