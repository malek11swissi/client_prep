package com.arabsoft.pfe.projet.model.securite;

import java.util.List;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.arabsoft.pfe.projet.model.framework.ObjetPersistant;
import com.arabsoft.pfe.projet.projection.securite.MonCompteUtilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "mail"}))
@Data
@NoArgsConstructor
public class Utilisateur extends ObjetPersistant {

    public Utilisateur(MonCompteUtilisateur monCompte) {
        this.nom = monCompte.getNom();
        this.prenom = monCompte.getPrenom();
        this.login = monCompte.getLogin();
        this.mail = monCompte.getMail();
        this.tel = monCompte.getTel();
        this.mobile = monCompte.getMobile();
        this.profil = new Profil(monCompte.getProfil());
    }

    public Utilisateur(String nom, String prenom, String mail, String login, String mdp, String tel, String mobile,
            Profil profil, LocalDate datenaissance, String ville, String pays, String addresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.tel = tel;
        this.mobile = mobile;
        this.profil = profil;
        this.datenaissance = datenaissance;
        this.ville = ville;
        this.pays = pays;
        this.addresse = addresse;
    }

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(length = 100, nullable = false)
    private String prenom;

    @Column(length = 200, nullable = false)
    private String mail;

    @Column(length = 30, nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String mdp;

    private String tel;

    private String mobile;

    private LocalDate datenaissance;

    private String ville;

    private String pays;

    private String addresse;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profil profil;

    @Transient
    private List<Droit> listDroit;

}
