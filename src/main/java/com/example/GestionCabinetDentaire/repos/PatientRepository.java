package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findById(Long id);
}
