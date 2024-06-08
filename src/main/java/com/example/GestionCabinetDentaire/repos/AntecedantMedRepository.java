package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.AntecendentMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedantMedRepository extends JpaRepository<AntecendentMedical,Long> {
}
