package com.example.challenge2_alkemy.controller;

import com.example.challenge2_alkemy.model.Users;
import com.example.challenge2_alkemy.service.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    //Clase donde se van a controlar los Endpoints para registrar y loguear un nuevo usuario
    @Autowired
    private OperationService oService;

    //Registrar Usuario
    @GetMapping("/")
    public String registerUser(Model model){
        model.addAttribute("user", new Users());
        return "homeMain";
    }

    //Guardar Usuario en la base de datos
    @PostMapping("/")
    public String saveUser(@Validated @ModelAttribute Users u, BindingResult result, Model model){
        if (result.hasErrors()) {
            System.out.println("Problems with date");
            return "redirect:/";
        } else {
            model.addAttribute("user", oService.saveUser(u));
            System.out.println("New registered user!");
        }
        return "redirect:/login";
    }

    //Loguear Usuario
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("login", new Users());
        return "login";
    }
}
