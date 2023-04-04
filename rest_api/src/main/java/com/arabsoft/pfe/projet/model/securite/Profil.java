package com.arabsoft.pfe.projet.model.securite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.arabsoft.pfe.projet.model.framework.ObjetPersistant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Profil extends ObjetPersistant{

    public Profil(String libelle) {
        this.libelle = libelle;
    }

    

    public Profil(String code, String libelle, boolean actif) {
        this.code = code;
        this.libelle = libelle;
        this.actif = actif;
    }

    @Column(length = 30, nullable = false, unique = true)
    private String code;

    @Column(length = 100, nullable = false)
    private String libelle;

    @Column(nullable = false)
    private boolean actif;
}
