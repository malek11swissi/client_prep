package com.arabsoft.pfe.projet.data.securite;

import com.arabsoft.pfe.projet.model.securite.Utilisateur;
import com.arabsoft.pfe.projet.projection.securite.MonCompteUtilisateur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUtilisateurData extends JpaRepository<Utilisateur, Long> {

        Utilisateur findByLogin(String login);

       /*  @Query(value = " SELECT utl.nom AS nom, utl.prenom  AS prenom, utl.mail AS mail, utl.login AS login, utl.tel AS tel, utl.mobile AS mobile, "
                        +
                        "        org.raisonSocial AS organisation, prf.libelle AS profil " +
                        " FROM   Utilisateur utl " +
                        " INNER  JOIN Organisation org ON utl.organisation = org " +
                        " INNER  JOIN Profil prf ON utl.profil = prf " +
                        " WHERE  utl.id = :id ")
        List<MonCompteUtilisateur> getMonCompte(@Param("id") Long id);
 */
        @Query(value = " SELECT utl.mdp " +
                        " FROM   Utilisateur utl " +
                        " WHERE  utl.id = :id ")
        String getPassword(@Param("id") Long id);

        @Modifying
        @Query(value = " UPDATE Utilisateur utl " +
                        " SET   utl.mdp = :mdp " +
                        " WHERE utl.id = :id  ")
        void updatePassword(@Param("id") Long id, @Param("mdp") String mdp);

        @Modifying
        @Query(value = " UPDATE Utilisateur utl " +
                        " SET   utl.nom = :nom, " +
                        "       utl.prenom = :prenom, " +
                        "       utl.mail = :mail, " +
                        "       utl.tel = :tel, " +
                        "       utl.mobile = :mobile " +
                        " WHERE utl.id = :id  ")
        void updateInfosUtilisateur(@Param("id") Long id, @Param("nom") String nom, @Param("prenom") String prenom,
                        @Param("mail") String mail, @Param("tel") String tel, @Param("mobile") String mobile);
}
