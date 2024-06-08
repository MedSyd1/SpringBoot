package com.example.GestionCabinetDentaire.entities;

import java.time.LocalDate;
import java.util.List;

import com.example.GestionCabinetDentaire.enums.GroupeSanguin;
import com.example.GestionCabinetDentaire.enums.Mutuelle;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Patient extends Personne{
    
    private LocalDate dateNaissance;
    private Mutuelle mutuelle;
    private GroupeSanguin groupeSanguin;
    private String profession;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    private List<AntecendentMedical> antecendentMedicals;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DossierMedical dossierMedical;
    
}
