package com.arabsoft.pfe.projet.controller.securite;

import com.arabsoft.pfe.projet.dto.securite.UtilisateurDto;
import com.arabsoft.pfe.projet.exception.BusinessException;
import com.arabsoft.pfe.projet.iservice.securite.IUtilisateurService;
import com.arabsoft.pfe.projet.model.framework.ObjetRetour;
import com.arabsoft.pfe.projet.model.securite.Utilisateur;
import com.arabsoft.pfe.projet.util.UtilMethod;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/utilisateur")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<ObjetRetour> recherche(@RequestBody Utilisateur utilisateur) {
        try {
            UtilisateurDto utilisateurDto = UtilMethod.getUtilisateurFromToken(request);
            //Long orgId = utilisateurDto.getOrganisation().getId();
            //utilisateur.setOrganisation(new Organisation(orgId));
            if (utilisateur.getId() == null) {
                Utilisateur utlExists = utilisateurService.getByLogin(utilisateur.getLogin());
                if (utlExists != null) {
                    throw new Exception("Nom de l'utilisateur existe déjà");
                }
                utilisateur.setMdp(bCryptPasswordEncoder.encode(utilisateur.getMdp()));
                utilisateurService.save(utilisateur);
            }
            utilisateur.setMdp(null);
            return new ResponseEntity<>(new ObjetRetour(utilisateur), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL001", e.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(value = "/guard")
    public ResponseEntity<ObjetRetour> guard() {
        try {
            return new ResponseEntity<>(new ObjetRetour(1), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL002", e.getMessage()),
                    HttpStatus.OK);
        }
    }

    /* @GetMapping(value = "/getmoncompte")
    public ResponseEntity<ObjetRetour> getMonCompte() {
        try {
            Utilisateur utl = utilisateurService.getMonCompte();
            return new ResponseEntity<>(new ObjetRetour(modelMapper.map(utl, UtilisateurDto.class)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL003", e.getMessage()),
                    HttpStatus.OK);
        }
    } */

    @PostMapping(value = "/changemotdepasse")
    public ResponseEntity<ObjetRetour> changeMotDePasse(@RequestBody UtilisateurDto utilisateur) {
        try {
            utilisateurService.changeMotDePasse(utilisateur);
            return new ResponseEntity<>(new ObjetRetour(true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                return new ResponseEntity<>(new ObjetRetour(((BusinessException) e).getCode(), e.getMessage()),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ObjetRetour("UTL004", e.getMessage()),
                        HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/connect")
    public ResponseEntity<ObjetRetour> connect() {
        try {
            return new ResponseEntity<>(new ObjetRetour("Serveur en marche"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL005", e.getMessage()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(value = "/crypthkh/{chaine}")
    public ResponseEntity<ObjetRetour> crypthkh(@PathVariable("chaine") String chaine) {
        try {
            return new ResponseEntity<>(new ObjetRetour(bCryptPasswordEncoder.encode(chaine)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL006", e.getMessage()),
                    HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateinfosutilisateur")
    public ResponseEntity<ObjetRetour> updateInfosUtilisateur(@RequestBody UtilisateurDto utilisateur) {
        try {
            utilisateurService.updateInfosUtilisateur(utilisateur);
            return new ResponseEntity<>(new ObjetRetour(true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ObjetRetour("UTL007", e.getMessage()),
                    HttpStatus.OK);
        }
    }
}
