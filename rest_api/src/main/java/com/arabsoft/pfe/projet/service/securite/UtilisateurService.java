package com.arabsoft.pfe.projet.service.securite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arabsoft.pfe.projet.data.securite.IUtilisateurData;
import com.arabsoft.pfe.projet.dto.securite.UtilisateurDto;
import com.arabsoft.pfe.projet.exception.BusinessException;
import com.arabsoft.pfe.projet.iservice.securite.IDroitService;
import com.arabsoft.pfe.projet.iservice.securite.IUtilisateurService;
import com.arabsoft.pfe.projet.model.framework.AppUser;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;
import com.arabsoft.pfe.projet.service.framework.GenericService;
import com.arabsoft.pfe.projet.util.UtilMethod;

@Service
@Transactional
public class UtilisateurService extends GenericService<Utilisateur, IUtilisateurData>
        implements IUtilisateurService, UserDetailsService {

    @Autowired
    private IUtilisateurData utilisateurData;

    @Autowired
    private IDroitService droitService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurData.findByLogin(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Login ou mot de passe incorrecte");
        } else {
            utilisateur.setListDroit(droitService.listByUtilisatur(utilisateur));
            List<SimpleGrantedAuthority> listAuthority = new ArrayList<>();
            utilisateur.getListDroit().forEach(d -> listAuthority.add(new SimpleGrantedAuthority(d.getCode())));
            return new AppUser(utilisateur.getLogin(), utilisateur.getMdp(), listAuthority, utilisateur);
        }
    }

    @Override
    public Utilisateur getByLogin(String login) {
        return utilisateurData.findByLogin(login);
    }

/*     @Override
    public Utilisateur getMonCompte() {
        UtilisateurDto utilisateurCourant = UtilMethod.getUtilisateurFromToken(request);
        return utilisateurData.getMonCompte(utilisateurCourant.getId()).stream().map(Utilisateur::new)
                .collect(Collectors.toList()).get(0);
    }
 */
    @Override
    public void changeMotDePasse(UtilisateurDto utilisateur) throws Exception {
        UtilisateurDto utilisateurCourant = UtilMethod.getUtilisateurFromToken(request);
        String currentPasswd = utilisateurData.getPassword(utilisateurCourant.getId());
        if (!bCryptPasswordEncoder.matches(utilisateur.getAncienMdp(), currentPasswd)) {
            throw new BusinessException("MDP_INVALID", "Mot de passe incorrect");
        }
        String nouvelPasswd = bCryptPasswordEncoder.encode(utilisateur.getMdp());
        utilisateurData.updatePassword(utilisateurCourant.getId(), nouvelPasswd);
    }

    @Override
    public void updateInfosUtilisateur(UtilisateurDto utilisateur) {
        UtilisateurDto utilisateurCourant = UtilMethod.getUtilisateurFromToken(request);
        utilisateurData.updateInfosUtilisateur(utilisateurCourant.getId(), utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getMail(), utilisateur.getTel(), utilisateur.getMobile());
    }

}
