package com.exam.compteservice.controllers;

import com.exam.compteservice.entities.Compte;
import com.exam.compteservice.services.CompteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompteController {
    private CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping(path = "/comptes/{id}")
    public Compte getCompte(@PathVariable Long id){
        return compteService.getCompte(id);
    }
}
