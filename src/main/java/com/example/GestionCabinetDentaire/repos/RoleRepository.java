package com.example.GestionCabinetDentaire.repos;

import com.example.GestionCabinetDentaire.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByNomRole(String nomRole);
}
