package com.arabsoft.pfe.projet.service.securite;

import com.arabsoft.pfe.projet.data.securite.IProfilDroitData;
import com.arabsoft.pfe.projet.iservice.securite.IProfilDroitService;
import com.arabsoft.pfe.projet.model.securite.Profil;
import com.arabsoft.pfe.projet.model.securite.ProfilDroit;
import com.arabsoft.pfe.projet.service.framework.GenericService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfilDroitService extends GenericService<ProfilDroit, IProfilDroitData> implements IProfilDroitService {

    @Autowired
    private IProfilDroitData profilDroitData;

    @Override
    public List<ProfilDroit> listByProfil(Profil profil) {
        return profilDroitData.findByProfil(profil);
    }

}
