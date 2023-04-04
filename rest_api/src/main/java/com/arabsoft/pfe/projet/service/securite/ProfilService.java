package com.arabsoft.pfe.projet.service.securite;

import com.arabsoft.pfe.projet.data.securite.IProfilData;
import com.arabsoft.pfe.projet.iservice.securite.IProfilService;
import com.arabsoft.pfe.projet.model.securite.Profil;
import com.arabsoft.pfe.projet.service.framework.GenericService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfilService extends GenericService<Profil, IProfilData> implements IProfilService{
    
    
}
