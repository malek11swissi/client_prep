package com.arabsoft.pfe.projet.model.securite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.arabsoft.pfe.projet.model.framework.ObjetPersistant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProfilDroit extends ObjetPersistant{

    public ProfilDroit(Profil profil, Droit droit) {
        this.profil = profil;
        this.droit = droit;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profil profil;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Droit droit;
}
