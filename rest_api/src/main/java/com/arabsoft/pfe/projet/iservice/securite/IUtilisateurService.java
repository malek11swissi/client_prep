package com.arabsoft.pfe.projet.iservice.securite;

import com.arabsoft.pfe.projet.dto.securite.UtilisateurDto;
import com.arabsoft.pfe.projet.iservice.framework.IGenericService;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;

public interface IUtilisateurService extends IGenericService<Utilisateur> {

    Utilisateur getByLogin(String login);

/*     Utilisateur getMonCompte();
 */
    void changeMotDePasse(UtilisateurDto utilisateur) throws Exception;

    void updateInfosUtilisateur(UtilisateurDto utilisateur);
}
