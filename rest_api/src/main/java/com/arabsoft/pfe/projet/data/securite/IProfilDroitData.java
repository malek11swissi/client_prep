package com.arabsoft.pfe.projet.data.securite;

import com.arabsoft.pfe.projet.model.securite.Profil;
import com.arabsoft.pfe.projet.model.securite.ProfilDroit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfilDroitData extends JpaRepository<ProfilDroit, Long>{

    List<ProfilDroit> findByProfil(Profil profil);
    
}
