package com.arabsoft.pfe.projet.iservice.securite;

import java.util.List;

import com.arabsoft.pfe.projet.iservice.framework.IGenericService;
import com.arabsoft.pfe.projet.model.securite.Droit;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;

public interface IDroitService extends IGenericService<Droit> {
    List<Droit> listByUtilisatur(Utilisateur utilisateur);
}
