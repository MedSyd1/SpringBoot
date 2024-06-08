package com.example.GestionCabinetDentaire.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.GestionCabinetDentaire.enums.CategorieActe;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActe;
    private Double prixDeBase;
    private String libelle;
    private CategorieActe categorieActe;
    @OneToMany(mappedBy = "acte")
    private List<InterventionMedecin> interventions;
}