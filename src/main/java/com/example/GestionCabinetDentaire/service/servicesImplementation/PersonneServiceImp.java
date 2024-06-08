package com.example.GestionCabinetDentaire.service.servicesImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionCabinetDentaire.entities.Personne;
import com.example.GestionCabinetDentaire.repos.PersonneRepository;
import com.example.GestionCabinetDentaire.service.servicesDeclaration.PersonneService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonneServiceImp  implements PersonneService{
    
    @Autowired 
    PersonneRepository personneRepository;
    
    @Override
    public Personne findPersonneByEmail(String email) {
        Personne personne = personneRepository.findByEmail(email);
        return personne;
    }

    @Override
    public void deletePersonneById(Long id) {
        personneRepository.deleteById(id);
        System.out.println("deleting the personne id : " + id);
    }

    @Override
    public Personne findPersonneById(Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        return personne.orElse(null);
    }


    
}
