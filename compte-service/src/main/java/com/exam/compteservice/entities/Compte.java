package com.exam.compteservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor
public class Compte {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double solde;
    private Date dateCreation;
    private boolean active;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "compte")
    private Collection<Operation> operations;
    private Long clientId;

    @Transient private Client client;
}

