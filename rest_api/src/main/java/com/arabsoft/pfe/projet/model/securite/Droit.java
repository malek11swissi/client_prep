package com.arabsoft.pfe.projet.model.securite;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.arabsoft.pfe.projet.model.framework.ObjetPersistant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Droit extends ObjetPersistant{
    @Column(length = 30, nullable = false, unique = true)
    private String code;

    @Column(length = 100, nullable = false)
    private String libelle;
}
