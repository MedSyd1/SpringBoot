package com.example.GestionCabinetDentaire.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class SituationFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSituationFinanciere;
    private LocalDate dateCreation;
    private Double montantGlobalRestant;
    private Double montantGlobalPaye;
    @OneToMany(mappedBy = "situationFinanciere")
    private List<Facture> factures;
    @OneToOne
    private DossierMedical dossierMedical;
    @ManyToOne
    private Caisse caisse;
}
