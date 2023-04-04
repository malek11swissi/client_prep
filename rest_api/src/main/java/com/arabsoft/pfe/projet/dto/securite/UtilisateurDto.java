package com.arabsoft.pfe.projet.dto.securite;

import java.util.List;

import com.arabsoft.pfe.projet.model.securite.Droit;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurDto {

    public UtilisateurDto(Utilisateur utilisateur, String token) {
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.mail = utilisateur.getMail();
        this.token = token;
        this.listDroit = utilisateur.getListDroit();
    }

    public UtilisateurDto(Long id, Long orgId, String login) {
        this.id = id;
        this.login = login;
    }

    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String login;
    private String mdp;
    private String tel;
    private String mobile;
    private String token;
    private String ancienMdp;
    private ProfileDto profil;
    private List<Droit> listDroit;

    public String getProfilLibelle() {
        return profil != null ? profil.getLibelle() : "";
    }

}
