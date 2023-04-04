package com.arabsoft.pfe.projet.data.securite;

import java.util.List;

import com.arabsoft.pfe.projet.model.securite.Droit;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDroitData extends JpaRepository<Droit, Long> {

    @Query(value = "FROM Droit drt " +
            " INNER JOIN ProfilDroit pfd ON pfd.droit = drt " +
            " INNER JOIN Profil pfl ON pfd.profil = pfl " +
            " INNER JOIN Utilisateur utl ON utl.profil = pfl " +
            " WHERE utl = :utl ")
    List<Droit> listByUtilisateur(@Param("utl") Utilisateur utl);
}
