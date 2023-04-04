package com.arabsoft.pfe.projet.data.securite;

import com.arabsoft.pfe.projet.model.securite.Profil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfilData extends JpaRepository<Profil, Long>{
    
}
