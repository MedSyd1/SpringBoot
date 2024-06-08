package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonneRepository extends JpaRepository<Personne,Long> {
    Personne  findByEmail(String email);
    Optional<Personne> findById(Long id);
    @Override
    void delete(Personne entity);
    void deleteById(Long id);

}
