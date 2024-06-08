package com.example.GestionCabinetDentaire.service.servicesDeclaration;

import org.springframework.ui.Model;

public interface LoginService {
    

    String redirectToLogin();
    String register(String email,String password,Model model);

}
