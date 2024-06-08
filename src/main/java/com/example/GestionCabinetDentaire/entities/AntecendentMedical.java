package com.example.GestionCabinetDentaire.entities;

import java.util.List;

import com.example.GestionCabinetDentaire.enums.CategorieAntecedentsMedicaux;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecendentMedical {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "antecendentMedicals")
    private List<Patient> patientsAvecCeAntecendentMedicale ;

    private String libelle;
    @Enumerated(value = EnumType.STRING)
    private CategorieAntecedentsMedicaux categorie;

}
