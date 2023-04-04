package com.arabsoft.pfe.projet.iservice.securite;

import java.util.List;

import com.arabsoft.pfe.projet.iservice.framework.IGenericService;
import com.arabsoft.pfe.projet.model.securite.Profil;
import com.arabsoft.pfe.projet.model.securite.ProfilDroit;

public interface IProfilDroitService extends IGenericService<ProfilDroit> {
    List<ProfilDroit> listByProfil(Profil profil);
}
