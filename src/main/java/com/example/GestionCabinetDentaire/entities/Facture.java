package com.example.GestionCabinetDentaire.entities;

import java.time.LocalDate;

import com.example.GestionCabinetDentaire.enums.TypePaiement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;
    private Double montantRestant;
    private Double montantPaye;
    private String etat;
    
    private LocalDate dateFacturation;
    private Double montantTotal;
    private TypePaiement typePaiement;
    @ManyToOne
    private SituationFinanciere situationFinanciere;
    @ManyToOne
    private Consultation consultation;

}
