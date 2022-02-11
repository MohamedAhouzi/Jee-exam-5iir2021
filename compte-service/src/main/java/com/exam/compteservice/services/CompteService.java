package com.exam.compteservice.services;

import com.exam.compteservice.entities.Compte;

public interface CompteService {
    Compte getCompte(Long id);
    void doVirement(Compte emetteur, Compte recepteur, double montant) throws Exception;
}
