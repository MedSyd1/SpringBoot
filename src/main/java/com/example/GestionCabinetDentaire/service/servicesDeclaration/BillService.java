package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import org.springframework.ui.Model;

import com.example.GestionCabinetDentaire.entities.Utilisateur;

public interface BillService {
  
    String  addBill(Utilisateur user,Model model,Long id , String idConsultation,String date,String etat,String reste,String mantant,String totle);
    String deleteBill(Utilisateur user,Long id,Long idBill,Model model);
}
