package com.arabsoft.pfe.projet.service.securite;

import java.util.List;

import javax.transaction.Transactional;

import com.arabsoft.pfe.projet.data.securite.IDroitData;
import com.arabsoft.pfe.projet.iservice.securite.IDroitService;
import com.arabsoft.pfe.projet.model.securite.Droit;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;
import com.arabsoft.pfe.projet.service.framework.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DroitService extends GenericService<Droit, IDroitData> implements IDroitService {

    @Autowired
    private IDroitData droitData;

    @Override
    public List<Droit> listByUtilisatur(Utilisateur utilisateur) {
        return droitData.listByUtilisateur(utilisateur);
    }
}
