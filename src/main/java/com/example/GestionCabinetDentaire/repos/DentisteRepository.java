package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Dentiste;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DentisteRepository extends JpaRepository<Dentiste,Long> {

    // List<Dentiste> findAll();
    // List<Dentiste> findByEmail(String email);;
    // Dentiste findByNom(String nom);
    // Optional<Dentiste> findById(Long id);

}
