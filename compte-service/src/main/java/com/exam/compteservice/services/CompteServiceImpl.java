package com.exam.compteservice.services;

import com.exam.compteservice.entities.Compte;
import com.exam.compteservice.entities.Operation;
import com.exam.compteservice.entities.TypeOperation;
import com.exam.compteservice.repositories.ClientServiceClient;
import com.exam.compteservice.repositories.CompteRepository;
import com.exam.compteservice.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {
    private ClientServiceClient serviceClient;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;

    public CompteServiceImpl(ClientServiceClient serviceClient, CompteRepository compteRepository, OperationRepository operationRepository) {
        this.serviceClient = serviceClient;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Compte getCompte(Long id){
        Compte compte = compteRepository.getById(id);
        compte.setClient(serviceClient.findClientById(compte.getClientId()));
        return compte;
    }

    @Override
    public void doVirement(Compte emetteur, Compte recepteur, double montant) throws Exception {
        if(emetteur.getSolde() >= montant)
            throw new Exception("makafish solde almezlot");
        if(!emetteur.isActive() || !recepteur.isActive())
            throw new Exception("an account is not active");
        emetteur.setSolde(emetteur.getSolde() - montant);
        recepteur.setSolde(recepteur.getSolde() + montant);
        operationRepository.save(new Operation(null, new Date(), montant, TypeOperation.CREDIT, emetteur));
        operationRepository.save(new Operation(null, new Date(), montant, TypeOperation.DEBIT, recepteur));
    }


}
