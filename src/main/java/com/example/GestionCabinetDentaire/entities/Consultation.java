package com.example.GestionCabinetDentaire.entities;

import java.time.LocalDate;
import java.util.List;

import com.example.GestionCabinetDentaire.enums.TypeConsultation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity         
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation;
    private LocalDate dateConsultation;
    @Enumerated(value = EnumType.STRING)
    private TypeConsultation typeConsultation;
    @OneToMany(mappedBy = "consultation")
    private List<Facture> factures;
    @OneToMany(mappedBy = "consultation")
    private List<InterventionMedecin> interventions;
    @ManyToOne
    private DossierMedical dossierMedical;

}
