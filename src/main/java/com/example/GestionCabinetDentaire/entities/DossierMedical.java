package com.example.GestionCabinetDentaire.entities;

import java.time.LocalDate;
import java.util.List;

import com.example.GestionCabinetDentaire.enums.StatutPaiement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedical {

    @Id
    private String numeroDossier;
    private LocalDate dateCreation;
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;
    @OneToMany(mappedBy = "dossierMedical")
    private List<Consultation> consultations;
    @OneToOne(mappedBy = "dossierMedical")
    private Patient patient;
    @ManyToOne
    private Dentiste medecinTraitant;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private SituationFinanciere situationFinanciere;
    
}
