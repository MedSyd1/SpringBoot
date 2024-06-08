package com.example.GestionCabinetDentaire.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Role {

    @Id
    private String nomRole;
    private List<String> privileges;

}
