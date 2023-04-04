package com.arabsoft.pfe.projet.model.forfaitprep;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class HistoriqueAchat {
    private LocalDate dateAchat;
    private String token;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "forfait_id")
    private Forfait forfait;
}
