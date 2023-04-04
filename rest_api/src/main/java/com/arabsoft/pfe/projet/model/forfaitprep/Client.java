package com.arabsoft.pfe.projet.model.forfaitprep;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.arabsoft.pfe.projet.model.securite.Utilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="client")
@Data
@NoArgsConstructor
public class Client extends Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long solde;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "historiqueAchat",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "forfait_id"))
    private Set<Forfait> forfaits = new HashSet<>();
}
