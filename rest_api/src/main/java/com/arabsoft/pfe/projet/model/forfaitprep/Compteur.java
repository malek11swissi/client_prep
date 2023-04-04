package com.arabsoft.pfe.projet.model.forfaitprep;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.arabsoft.pfe.projet.model.securite.Utilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="compteur")
@Data
@NoArgsConstructor
public class Compteur extends Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
